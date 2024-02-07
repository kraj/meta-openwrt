# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt GPS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/ugps.git;a=summary"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2bf63b09608cf97d9dbafe99c7ea23fe"
SECTION = "base"
DEPENDS = "libubox ubus"

SRC_URI = "git://git.openwrt.org/project/ugps.git;protocol=https;branch=master \
          "

SRCREV = "511a5b3c84fa715ef0305cf26c98619c12a4867a"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

FILES:${PN}  += "${libdir}/*"
