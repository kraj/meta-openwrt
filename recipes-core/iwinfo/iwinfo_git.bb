# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Library for accessing wireless device drivers"
HOMEPAGE = "http://git.openwrt.org/?p=project/iwinfo.git;a=summary"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "base"
DEPENDS += "uci lua5.1 ubus"

SRCREV = "4a43b0d40ba50a21de1d47e7bf0f759be9cf646a"

inherit openwrt

SRC_URI = "git://git.openwrt.org/project/iwinfo.git;protocol=https;branch=master \
           file://0001-Makefile-LDFLAGS-set-liblua5.1-for-lua-lib.patch \
           file://0002-fix-order-of-linker-cmdline-to-help-linking.patch \
           file://0003-Replace-typedef-loaL_reg-with-luaL_Reg.patch \
           "

S = "${WORKDIR}/git"

CFLAGS += "-fPIC"

# iwinfo breaks with parallel make
PARALLEL_MAKE = ""

FILES:${PN}-dev += "${libdir}/libiwinfo.so"

do_install() {
	install -D -m 0755 ${B}/libiwinfo.so.0 ${D}${libdir}/libiwinfo.so.0
	ln -sf libiwinfo.so.0 ${D}${libdir}/libiwinfo.so
        install -D -m 0755 ${B}/iwinfo.so ${D}${libdir}/lua/5.1/iwinfo.so
        install -D -m 0755 ${B}/iwinfo ${D}${bindir}/iwinfo
	install -D -m 0644 ${S}/include/iwinfo.h ${D}${includedir}/iwinfo.h
	install -D -m 0644 ${S}/include/iwinfo/utils.h ${D}${includedir}/iwinfo/utils.h
}
