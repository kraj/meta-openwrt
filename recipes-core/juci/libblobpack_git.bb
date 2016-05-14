# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "A library for packing arbitrary data into binary cross platform blobs"
HOMEPAGE = "https://github.com/mkschreder/libblobpack"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://README.md;md5=5b6e4258f0d17eb254f7af0b3e70c298"
SECTION = "libs"

SRCREV = "d67ed0db60594ee634f0115b797064e9ca17b8c7"
SRC_URI = "git://github.com/mkschreder/libblobpack \
           file://0001-examples-simple-Remove-unused-code.patch \
           "

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${libdir} ${D}${includedir}/blobpack
	install -m 0755 ${B}/libblobpack.so ${D}${libdir}
	install -m 0644 ${S}/*.h ${D}${includedir}/blobpack
}

FILES_SOLIBSDEV = ""
FILES_${PN} = "${libdir}/*.so"

