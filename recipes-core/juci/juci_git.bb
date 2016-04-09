# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "JUCI JavaScript Webgui for embedded devices running OpenWRT"
HOMEPAGE = "https://github.com/mkschreder/juci"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=87212b5f1ae096371049a12f80034f32"
SECTION = "apps"

SRCREV = "192f9ddb984c1b6ee7ec410329d65b43ab63833e"
SRC_URI = "git://github.com/mkschreder/juci"
SRC_URI += "file://0001-juci-pin-grunt-to-0.4.1-for-grunt-angular-gettext.patch"

S = "${WORKDIR}/git"

inherit npm-install

NPM_INSTALL_append = " uglify-js"
DEPENDS += "jucid lua5.1 grunt-cli-native"

do_compile() {
	oe_runmake node_modules
}

do_install_append() {
	oe_runmake
	oe_runmake DESTDIR='${D}' install
}

FILES_${PN} += "/www ${datadir}/lua"
