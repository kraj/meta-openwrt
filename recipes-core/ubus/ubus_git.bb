# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt system message/RPC bus"
HOMEPAGE = "https://git.openwrt.org/?p=project/ubus.git;a=summary"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://ubusd.c;beginline=1;endline=12;md5=1b6a7aecd35bdd25de35da967668485d"
SECTION = "base"
DEPENDS = "json-c libubox"

SRC_URI = "git://git.openwrt.org/project/ubus.git;protocol=https;branch=master"

SRCREV = "2bebf93cd3343fe49f22a05ef935e460d2d44f67"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

do_install:append () {
    install -dm 0755 ${D}/sbin
    ln -s /usr/sbin/ubusd ${D}/sbin/ubusd
}

TOOLCHAIN = "gcc"
