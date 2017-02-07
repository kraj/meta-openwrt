# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt Network interface configuration daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/netifd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"
SECTION = "base"
DEPENDS = "json-c libubox ubus libnl uci"

SRCREV_netifd = "64a655d8ffa9f0cea1bbdd35cac6b3b99b865270"
SRCREV_openwrt = "${OPENWRT_SRCREV}"

SRC_URI = "\
    git://git.openwrt.org/project/netifd.git;name=netifd \
    git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;branch=chaos_calmer \
    file://network.config \
    file://100-Fix-IFF_LOWER_UP-define.patch \
"

inherit cmake pkgconfig openwrt

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

    mkdir -p ${D}${sysconfdir}/rc.d
    ln -s ../init.d/network ${D}${sysconfdir}/rc.d/S20network
    ln -s ../init.d/network ${D}${sysconfdir}/rc.d/K90network

    mkdir -p ${D}/sbin
    ln -s /usr/sbin/netifd ${D}/sbin/netifd
}
