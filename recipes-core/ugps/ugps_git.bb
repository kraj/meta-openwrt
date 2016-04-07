# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt GPS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/ugps.git;a=summary"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2bf63b09608cf97d9dbafe99c7ea23fe"
SECTION = "base"
DEPENDS = "libubox ubus"

SRCREV = "971e6703eb9bed936cc62cd335105bd2acca14ef"
SRC_URI = "git://git.openwrt.org/project/ugps.git \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

CFLAGS += "-D_DEFAULT_SOURCE"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
