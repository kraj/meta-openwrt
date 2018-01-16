# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt Network interface configuration daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/netifd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"
SECTION = "base"
DEPENDS = "json-c libubox ubus libnl uci"
RDEPENDS_${PN} += " bridge-utils kernel-module-bridge "

SRCREV_pn-netifd = "650758b16e5185505a3fbc1307949340af70b611"
inherit cmake pkgconfig openwrt openwrt-services

SRCREV_openwrt = "${OPENWRT_SRCREV}"

CONFLICTS += "ifupdown"

SRC_URI = "\
    git://git.openwrt.org/project/netifd.git;name=netifd \
    git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;branch=lede-17.01 \
    file://network.config \
    file://100-Fix-IFF_LOWER_UP-define.patch \
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
"

do_install_append() {
    cp -a ${S}/openwrt/package/network/config/netifd/files/* ${D}
    cp -a ${S}/scripts/* ${D}/lib/netifd
    chown -R root:root ${D}/*

    install -Dm 0755 ${WORKDIR}/network.config ${D}${sysconfdir}/config/network

    mkdir -p ${D}/sbin
    ln -s /usr/sbin/netifd ${D}/sbin/netifd
}
