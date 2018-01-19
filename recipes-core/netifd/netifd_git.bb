# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt Network interface configuration daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/netifd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"
SECTION = "base"
DEPENDS = "json-c libubox ubus libnl uci"
RDEPENDS_${PN} += " bridge-utils kernel-module-bridge base-files-scripts-openwrt "

inherit cmake pkgconfig openwrt openwrt-services update-alternatives openwrt-base-files

SRCREV_netifd = "650758b16e5185505a3fbc1307949340af70b611"
SRCREV_openwrt = "${OPENWRT_SRCREV}"

SRC_URI = "\
    git://git.openwrt.org/project/netifd.git;name=netifd \
    file://100-Fix-IFF_LOWER_UP-define.patch \
    file://network.config \
"

S = "${WORKDIR}/git"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -Wno-error=cpp"

FILES_${PN} += "\
    /usr/share/udhcpc/default.script \
    /lib/netifd/dhcp.script \
    /lib/netifd/utils.sh \
    /lib/netifd/netifd-wireless.sh \
    /lib/netifd/netifd-proto.sh \
    /lib/netifd/proto/dhcp.sh \
    /lib/network/config.sh \
    /lib/functions/network.sh \
    ${@bb.utils.contains('IMAGE_INSTALL', 'base-files ', '', '${sysconfdir}/config/network', d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', '/sbin/wifi', '', d)} \
"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/network \
	$(sysconfdir)/config/wireless \
	"

ALTERNATIVE_${PN} = "ifup ifdown"

ALTERNATIVE_PRIORITY = "40"

ALTERNATIVE_LINK_NAME[ifup] = "${base_sbindir}/ifup"
ALTERNATIVE_LINK_NAME[ifdown] = "${base_sbindir}/ifdown"

do_install_append() {
    cp -dR --preserve=mode,links ${S}/openwrt/package/network/config/netifd/files/* ${D}/
    cp -dR --preserve=mode,links ${S}/scripts/* ${D}${base_libdir}/netifd/

    install -Dm 0644 ${S}/openwrt/package/base-files/files/lib/functions/network.sh ${D}${base_libdir}/functions/network.sh
    install -Dm 0755 ${S}/openwrt/package/base-files/files/etc/uci-defaults/12_network-generate-ula ${D}${sysconfdir}/uci-defaults/12_network-generate-ula
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'install -Dm 0755 ${S}/openwrt/package/base-files/files/sbin/wifi ${D}${base_sbindir}/wifi', '', d)}
    install -dm 0755 ${D}${sysconfdir}/config
    # If config_generate is not present we need a default network config
    ${@bb.utils.contains('IMAGE_INSTALL', 'base-files ', '', 'install -Dm 0644 ${WORKDIR}/network.config ${D}${sysconfdir}/config/network', d)}
    # FIXME: Handle wireless case without config_generate

    install -dm 0755 ${D}/sbin
    ln -sf /usr/sbin/netifd ${D}/sbin/netifd

    # Be prepared for both procd and systemd/sysvinit style module loading
    install -dm 0755 ${D}/etc/modules.d ${D}/etc/modules-load.d
    echo "bridge" >${D}/etc/modules.d/30-bridge
    echo "bridge" >${D}/etc/modules-load.d/bridge.conf
}
