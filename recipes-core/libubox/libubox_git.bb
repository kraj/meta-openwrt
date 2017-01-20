# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "C utility functions for OpenWrt"
HOMEPAGE = "http://git.openwrt.org/?p=project/libubox.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://uloop.c;beginline=1;endline=17;md5=f151c0422668fa4c8f91d2caf5267b3e"
SECTION = "base"
DEPENDS += "json-c lua5.1"

SRCREV = "7f671b1e68a6664b5baf3e3cffc1bb0880984267"
SRC_URI = "git://git.openwrt.org/project/libubox.git \
          "

inherit cmake pkgconfig openwrt
EXTRA_OECMAKE += "-DBUILD_EXAMPLES=ON -DBUILD_LUA=ON -DCMAKE_SKIP_RPATH=ON"
OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"
S = "${WORKDIR}/git"

do_install_append() {
	install -d ${D}${bindir} ${D}${includedir}/libubox
	install -m 0755 ${B}/examples/*-example ${D}${bindir}
	install -m 0755 ${S}/examples/uloop-example.lua ${D}${bindir}
	install -m 0755 ${S}/examples/uloop_pid_test.sh ${D}${bindir}
	install -m 0644 ${S}/*.h ${D}${includedir}/libubox
}

PACKAGES =+ "${PN}-examples"

FILES_${PN}-examples += "${bindir}/*-example \
                         ${bindir}/uloop-example.lua \
                         ${bindir}/uloop_pid_test.sh"

