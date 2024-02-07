# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "libubox HTTP client library"
HOMEPAGE = "http://git.openwrt.org/?p=project/uclient.git;a=summary"
LICENSE = "0BSD"
LIC_FILES_CHKSUM = "file://uclient-backend.h;beginline=1;endline=17;md5=b96bb2c7c7edb5a7cff262e23626e382"
SECTION = "base"
DEPENDS = "libubox ustream-ssl"

SRC_URI = "git://git.openwrt.org/project/uclient.git;protocol=https;branch=master \
          "

SRCREV = "6a6011df3429ffa5958d12b1327eeda4fd9daa47"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt
