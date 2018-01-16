# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Small stream SSL library"
HOMEPAGE = "http://git.openwrt.org/?p=project/ustream-ssl.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ustream-ssl.h;beginline=1;endline=17;md5=f633104677420342f142ab4835e04031"
SECTION = "base"
DEPENDS = "libubox openssl"
RDEPENDS_${PN} += "libcrypto libssl"

SRCREV = "ec80adaa1b47f28d426fa19c692011ce60b992d6"
SRC_URI = "git://git.openwrt.org/project/ustream-ssl.git \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

do_install_append() {
	install -d ${D}${includedir}/libubox
	install -m 0644 ${S}/*.h ${D}${includedir}/libubox

	install -dm 0755 ${D}/lib
	mv ${D}/usr/lib/libustream-ssl.so ${D}/lib/libustream-ssl.so
	rmdir ${D}/usr/lib
}

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${base_libdir}/*"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug"
