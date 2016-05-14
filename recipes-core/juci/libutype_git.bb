# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "micro types library for use with ubus2"

HOMEPAGE = "https://github.com/mkschreder/libutype"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=d41d8cd98f00b204e9800998ecf8427e"
SECTION = "libs"

SRCREV = "7a5c74c80c037ffebd4f10c61b31b186c8a22fed"
SRC_URI = "git://github.com/mkschreder/libutype \
           file://0001-Add-LDFLAGS-to-linker-cmdline.patch \
"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "DESTDIR=${D} BUILD_DIR=${B}"

do_install() {
	oe_runmake install
}

FILES_SOLIBSDEV = ""
FILES_${PN} = "${libdir}/*.so"

RDEPENDS_${PN} += "libusys"
