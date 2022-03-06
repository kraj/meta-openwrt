# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Library and utility for the Unified Configuration Interface for OpenWrt"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://uci.h;beginline=1;endline=13;md5=0ee862ed12171ee619c8c2eb7eff77f2"
SECTION = "base"
DEPENDS = "libubox lua5.1"

SRC_URI = "git://git.openwrt.org/project/uci.git;name=uci \
          "

SRCREV_uci = "52bbc99f69ea6f67b6fe264f424dac91bde5016c"

S = "${WORKDIR}/git"
OR = "${S}/openwrt/package/system/uci/files"

inherit cmake pkgconfig openwrt openwrt-base-files

SRCREV_openwrt = "${OPENWRT_SRCREV}"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"

do_install:append() {
    install -Dm 0755 ${OR}/lib/config/uci.sh ${D}${base_libdir}/config/uci.sh

    mkdir -p ${D}/sbin
    mkdir -p ${D}/usr/sbin
    ln -s /usr/bin/uci ${D}/usr/sbin/uci
    ln -s /usr/bin/uci ${D}/sbin/uci
}

FILES:${PN} += "${base_libdir}"
