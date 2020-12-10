# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt MDNS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/mdnsd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=12;md5=ce0be9da20a926574bf76c1285343bef"
SECTION = "base"
DEPENDS = "json-c libcap libubox ubus"

SRC_URI = "git://git.openwrt.org/project/mdnsd.git \
          "
SRCREV = "59e4fc98162d253b4e5ecd110f7bc5ea3962e221"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-services openwrt

TARGET_CFLAGS_append = " -Wno-error=array-bounds"

FILES_${PN}  += "${libdir}/*"
