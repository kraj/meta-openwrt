# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Tiny HTTP server"
HOMEPAGE = "http://git.openwrt.org/?p=project/uhttpd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=ba30601dd30339f7ff3d0ad681d45679"
SECTION = "base"
DEPENDS = "libubox ubus json-c ustream-ssl"

SRCREV = "fe01ef3f52adae9da38ef47926cd50974af5d6b7"
SRC_URI = "git://git.openwrt.org/project/uhttpd.git \
          "

inherit cmake pkgconfig openwrt

S = "${WORKDIR}/git"

CFLAGS += "-D_DEFAULT_SOURCE"

EXTRA_OECMAKE = "-DTLS_SUPPORT=ON -DLUA_SUPPORT=ON -DUBUS_SUPPORT=ON"
FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
