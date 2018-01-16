# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Library and utility for the Unified Configuration Interface for OpenWrt"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://uci.h;beginline=1;endline=13;md5=0ee862ed12171ee619c8c2eb7eff77f2"
SECTION = "base"
DEPENDS = "libubox lua5.1"

SRCREV = "141b64ef84f43c954e665865b1bbf216fbf7c05f"
SRC_URI = "git://git.openwrt.org/project/uci.git;branch=lede-17.01 \
           file://uci.sh \
	   file://0100-prevent-redefinition-of-stddef.path \
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
