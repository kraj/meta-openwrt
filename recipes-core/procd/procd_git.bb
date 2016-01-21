# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "procd is the new OpenWrt process management daemon written in C"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SECTION = "base"
DEPENDS = "libubox ubus json-c"

SRCREV = "ef490722885a5c708c70dff656d094c7043ae081"
SRC_URI = "git://nbd.name/luci2/procd.git \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug"
