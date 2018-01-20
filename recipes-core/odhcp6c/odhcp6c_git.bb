# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "OpenWrt DHCPv6 client"
HOMEPAGE = "http://git.openwrt.org/?p=project/odhcp63.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://src/odhcp6c.c;beginline=1;endline=13;md5=ec68aaa1b08cfe27d1dc420d2edc0fc7"
SECTION = "base"
DEPENDS = "libubox"

SRCREV_odhcp6c = "c13b6a05dbd9174356cc4b7fd1edf39445efd982"

SRC_URI = "\
          git://git.openwrt.org/project/odhcp6c.git;name=odhcp6c \
          "

S = "${WORKDIR}/git"
OF = "${S}/openwrt/package/network/ipv6/odhcp6c/files"

inherit cmake pkgconfig openwrt openwrt-services openwrt-base-files

SRCREV_openwrt = "${OPENWRT_SRCREV}"

do_install_append() {
    install -Dm 0755 ${OF}/dhcpv6.sh ${D}${base_libdir}/netifd/proto/dhcpv6.sh
    install -Dm 0755 ${OF}/dhcpv6.script ${D}${base_libdir}/netifd/dhcpv6.script
}

FILES_${PN} += "\
               ${base_libdir}/netifd/proto/dhcpv6.sh \
               ${base_libdir}/netifd/dhcpv6.script \
               "

RRECOMMENDS_${PN} += "netifd"
