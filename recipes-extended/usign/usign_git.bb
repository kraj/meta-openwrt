# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt tiny signify replacement"
HOMEPAGE = "https://git.openwrt.org/?p=project/usign.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=a51760fc5328fc7e0e27a5af562c6cfa"
SECTION = "base"

SRC_URI = "git://git.openwrt.org/project/usign.git;protocol=https;branch=master \
          "
SRCREV = "f1f65026a94137c91b5466b149ef3ea3f20091e9"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE += "${EXTRA_OECONF}"

# avoids build breaks when using no-static-libs.inc
DISABLE_STATIC = ""

PACKAGECONFIG ??= "ubox"

PACKAGECONFIG[ubox] = "-DUSE_LIBUBOX=ON,,libubox,"
