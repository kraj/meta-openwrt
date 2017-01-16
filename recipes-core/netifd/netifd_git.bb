# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt Network interface configuration daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/netifd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"
SECTION = "base"
DEPENDS = "json-c libubox ubus libnl uci"

SRCREV = "64a655d8ffa9f0cea1bbdd35cac6b3b99b865270"
SRC_URI = "\
    git://git.openwrt.org/project/netifd.git \
    file://100-Fix-IFF_LOWER_UP-define.patch \
"

inherit cmake pkgconfig openwrt

S = "${WORKDIR}/git"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -Wno-error=cpp"
