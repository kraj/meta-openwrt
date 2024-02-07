# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Small stream SSL library"
HOMEPAGE = "http://git.openwrt.org/?p=project/ustream-ssl.git;a=summary"
LICENSE = "0BSD"
LIC_FILES_CHKSUM = "file://ustream-ssl.h;beginline=1;endline=17;md5=f633104677420342f142ab4835e04031"
SECTION = "base"
DEPENDS = "libubox openssl"

SRC_URI = "git://git.openwrt.org/project/ustream-ssl.git;protocol=https;branch=master \
          "

SRCREV = "868fd8812f477c110f9c6c5252c0bd172167b94c"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

do_install:append() {
	install -d ${D}${includedir}/libubox
	install -m 0644 ${S}/*.h ${D}${includedir}/libubox

	install -dm 0755 ${D}${base_libdir}
	mv ${D}${libdir}/libustream-ssl.so ${D}${base_libdir}/libustream-ssl.so
	rmdir --ignore-fail-on-non-empty ${D}${libdir}
}

FILES:${PN}  += "${base_libdir}/*"
FILES:${PN}-dbg  += "${libdir}/lua/5.*/.debug"

RDEPENDS:${PN} += "libcrypto libssl"
