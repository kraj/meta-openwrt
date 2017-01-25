# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Library and utility for the Unified Configuration Interface for OpenWrt"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://uci.h;beginline=1;endline=13;md5=0ee862ed12171ee619c8c2eb7eff77f2"
SECTION = "base"
DEPENDS = "libubox lua5.1"

SRCREV = "2cd94fd505b293e657278e31deb32a7d66d20714"
SRC_URI = "git://git.openwrt.org/project/uci.git \
           file://uci.sh \
"

inherit cmake pkgconfig openwrt

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"

S = "${WORKDIR}/git"

do_install_append() {
    install -Dm 0755 ${WORKDIR}/uci.sh ${D}${base_libdir}/config/uci.sh

    mkdir -p ${D}/sbin
    mkdir -p ${D}/usr/sbin
    ln -s /usr/bin/uci ${D}/usr/sbin/uci
    ln -s /usr/bin/uci ${D}/sbin/uci
}

FILES_${PN} += "${base_libdir}"
