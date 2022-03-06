# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "This library is minimum, easy-to-use, C implementation for xml file parsing."
HOMEPAGE = "http://www.libroxml.net"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://License.txt;md5=81cba52d2de829c8be3d167618e6b8b6"
SECTION = "libs"
DEPENDS = ""

PV = "3.0.2+git${SRCPV}"
SRCREV = "34257124eefe0ca095ed54681c818381070ed549"
SRC_URI = "git://github.com/blunderer/libroxml.git \
         "

inherit cmake pkgconfig
EXTRA_OECMAKE += "-DBUILD_TESTING=OFF"
S = "${WORKDIR}/git"
