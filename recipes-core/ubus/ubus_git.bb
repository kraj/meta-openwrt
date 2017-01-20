# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt system message/RPC bus"
HOMEPAGE = "http://git.openwrt.org/?p=project/libubox.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ubusd.c;beginline=1;endline=12;md5=1b6a7aecd35bdd25de35da967668485d"
SECTION = "base"
DEPENDS = "json-c libubox"

SRCREV = "8bb34756ce2bd684745d3a49a134dae03073b452"
SRC_URI = "git://git.openwrt.org/project/ubus.git"

inherit cmake pkgconfig openwrt

do_install_append () {
    install -dm 0755 ${D}/sbin
    ln -s /usr/sbin/ubusd ${D}/sbin/ubusd
}

S = "${WORKDIR}/git"
