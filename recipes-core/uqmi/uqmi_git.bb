# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt uqmi utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/uqmi.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=20;md5=3f7041e5710007661d762bb6043a69c6"
SECTION = "base"
DEPENDS = "libubox json-c"

SRCREV = "c61815319c1c0e76898048a19151f30844a6989c"
SRC_URI = "git://git.openwrt.org/project/uqmi.git \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"
B = "${S}"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
