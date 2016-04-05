# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Small stream SSL library"
HOMEPAGE = "http://git.openwrt.org/?p=project/ustream-ssl.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ustream-ssl.h;beginline=1;endline=17;md5=f633104677420342f142ab4835e04031"
SECTION = "base"
DEPENDS = "libubox openssl"
RDEPNDS_${PN} += "libcrypto libssl"

SRCREV = "c2d73c22618e8ee444e8d346695eca908ecb72d3"
SRC_URI = "git://git.openwrt.org/project/ustream-ssl.git \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

do_install_append() {
	install -d ${D}${includedir}/libubox
	install -m 0644 ${S}/*.h ${D}${includedir}/libubox
}

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug"
