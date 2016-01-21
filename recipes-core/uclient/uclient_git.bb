# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "libubox HTTP client library"
HOMEPAGE = "http://git.openwrt.org/?p=project/uclient.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://uclient-backend.h;beginline=1;endline=17;md5=b96bb2c7c7edb5a7cff262e23626e382"
SECTION = "base"
DEPENDS = "libubox ustream-ssl"

SRCREV = "4edb1426cef9eb07aa52dab2c34ab51f64e10f74"
SRC_URI = "git://git.openwrt.org/project/uclient.git \
          "

inherit cmake pkgconfig openwrt

OECMAKE_C_FLAGS += "-Wno-error=discarded-qualifiers"

S = "${WORKDIR}/git"
