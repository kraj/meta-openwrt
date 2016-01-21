# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "C utility functions for OpenWrt"
HOMEPAGE = "http://git.openwrt.org/?p=project/libubox.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://uloop.c;beginline=1;endline=17;md5=9bed33188dd18fa8fec97a710e234273"
SECTION = "base"
DEPENDS += "json-c lua5.1"

SRCREV = "136a5196266d03d537f822c4e67d2fde2ed59505"
SRC_URI = "git://git.openwrt.org/project/libubox.git \
          "

inherit cmake pkgconfig openwrt
EXTRA_OECMAKE += "-DBUILD_EXAMPLES=ON -DBUILD_LUA=ON"
OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"
S = "${WORKDIR}/git"

do_install_append() {
	install -d ${D}${bindir}
	install -m 0755 ${B}/examples/*-example ${D}${bindir}
	install -m 0755 ${S}/examples/uloop-example.lua ${D}${bindir}
	install -m 0755 ${S}/examples/uloop_pid_test.sh ${D}${bindir}
}

PACKAGES =+ "${PN}-examples"

FILES_${PN}-examples += "${bindir}/*-example \
                         ${bindir}/uloop-example.lua \
                         ${bindir}/uloop_pid_test.sh"

