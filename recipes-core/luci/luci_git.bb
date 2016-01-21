# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt system message/RPC bus"
HOMEPAGE = "http://git.openwrt.org/?p=project/libubox.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ubusd.c;beginline=1;endline=12;md5=1b6a7aecd35bdd25de35da967668485d"
SECTION = "base"
DEPENDS = "json-c libubox lua5.1"

SRCREV = "86326e0deff92a485ffd47e22ac70194abb3fd66"
SRC_URI = "git://git.openwrt.org/project/luci.git"

inherit cmake pkgconfig openwrt

S = "${WORKDIR}/git"
