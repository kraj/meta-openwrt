# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt uqmi utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/uqmi.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=20;md5=8c7ce85ebfe23634010c75c30c3eb223"
SECTION = "base"
DEPENDS = "libubox json-c"

SRCREV = "c61815319c1c0e76898048a19151f30844a6989c"
SRC_URI = "git://git.openwrt.org/project/uqmi.git \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
