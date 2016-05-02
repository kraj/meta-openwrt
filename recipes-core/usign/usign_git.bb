# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt tiny signify replacement"
HOMEPAGE = "https://git.openwrt.org/?p=project/usign.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=a51760fc5328fc7e0e27a5af562c6cfa"
SECTION = "base"

SRCREV = "3f3aeceb15f9056cc4eb2f7e610d1cb067337c24"
SRC_URI = "git://git.openwrt.org/project/usign.git \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "${EXTRA_OECONF}"

# avoids build breaks when using no-static-libs.inc
DISABLE_STATIC = ""

PACKAGECONFIG ??= "ubox"

PACKAGECONFIG[ubox] = "-DUSE_LIBUBOX=ON,,libubox,"

