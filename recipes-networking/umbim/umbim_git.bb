# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt MBIM modem utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/umbim.git;a=summary"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://mbim.h;beginline=1;endline=13;md5=8c7ce85ebfe23634010c75c30c3eb223"
SECTION = "base"
DEPENDS = "libubox"

SRC_URI = "git://git.openwrt.org/project/umbim.git;protocol=https;branch=master \
          "
SRCREV = "184b707ddaa0acee84d02e0ffe599cb8b67782bd"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

TARGET_CFLAGS:append = " -Wno-error=address-of-packed-member"

FILES:${PN}  += "${libdir}/*"
