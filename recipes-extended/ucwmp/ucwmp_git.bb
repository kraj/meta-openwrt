# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Tiny TR-069 (CWMP) client for OpenWrt"
HOMEPAGE = "http://git.openwrt.org/?p=project/ucwmp.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://state.c;beginline=1;endline=13;md5=1a7da0d1097bb0cadc4ce0992bf9d641"
SECTION = "base"
DEPENDS = "json-c uci uclient ubus libubox libroxml"

SRCREV = "a9d51e5afbfde6a9b219e373eae213a59c145524"
SRC_URI = "git://git.openwrt.org/project/ucwmp.git \
           file://0001-add-missing-headers-for-getting-printf-and-str-funct.patch \
           file://0002-add-missing-header-include-for-json.h.patch \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
