# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "micro system library (for use with ubus2)"

HOMEPAGE = "https://github.com/mkschreder/libusys"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://src/uloop.c;beginline=1;endline=17;md5=9bed33188dd18fa8fec97a710e234273"
SECTION = "libs"

DEPENDS += "libutype"
RDEPENDS_${PN} += "libutype"

SRCREV = "27c5f81c80a3ea113378f56d4fc0e8fd903e7abe"
SRC_URI = "git://github.com/mkschreder/libusys"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "DESTDIR=${D} BUILD_DIR=${B}"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
	oe_runmake install
}

FILES_SOLIBSDEV = ""
FILES_${PN} = "${libdir}/*.so"

