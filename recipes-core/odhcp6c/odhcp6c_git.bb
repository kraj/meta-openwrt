# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "OpenWrt DHCPv6 client"
HOMEPAGE = "http://git.openwrt.org/?p=project/odhcp63.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://src/odhcp6c.c;beginline=1;endline=13;md5=41d01a2c8e6a8ef58b8e5f18e68118a8"
SECTION = "base"
DEPENDS = "libubox"

SRCREV_odhcp6c = "474b5a3a9a25f0aa12e69afd72d7661638ad879d"

SRC_URI = "\
          git://git.openwrt.org/project/odhcp6c.git;name=odhcp6c \
          file://0001-dhcpv6-Fix-strncpy-bounds-and-initialize-struct-to-0.patch \
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
