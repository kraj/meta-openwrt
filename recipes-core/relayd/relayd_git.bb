# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt IPv4 pseudo-bridge routing daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/relayd.git;a=summary"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=86aad799085683e0a2e1c2684a20bab2"
SECTION = "base"
DEPENDS = "libubox"

SRCREV = "83dba5d525a3b7c2ae4fcb24961143bfcfc93ba7"
SRC_URI = "git://git.openwrt.org/project/relayd.git \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
