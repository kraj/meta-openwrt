# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt GPS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/ugps.git;a=summary"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2bf63b09608cf97d9dbafe99c7ea23fe"
SECTION = "base"
DEPENDS = "libubox ubus"

SRC_URI = "git://git.openwrt.org/project/ugps.git \
          "

SRCREV = "971e6703eb9bed936cc62cd335105bd2acca14ef"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

CFLAGS += "-D_DEFAULT_SOURCE"

FILES_${PN}  += "${libdir}/*"
