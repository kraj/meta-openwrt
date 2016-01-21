# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt Network interface configuration daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/netifd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"
SECTION = "base"
DEPENDS = "json-c libubox ubus libnl uci"

SRCREV = "b3f60b48501319e93e846bbf23881666cee947bc"
SRC_URI = "git://git.openwrt.org/project/netifd.git"

inherit cmake pkgconfig openwrt

S = "${WORKDIR}/git"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -Wno-error=cpp"
