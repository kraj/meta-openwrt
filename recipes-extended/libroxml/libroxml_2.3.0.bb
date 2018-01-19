# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "This library is minimum, easy-to-use, C implementation for xml file parsing."
HOMEPAGE = "http://www.libroxml.net"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://License.txt;md5=81cba52d2de829c8be3d167618e6b8b6"
SECTION = "libs"
DEPENDS = ""

SRC_URI = "http://download.libroxml.net/pool/v2.x/${PN}-${PV}.tar.gz"

SRC_URI[sha256sum] = "1da8f20b530eba4409f2b217587d2f1281ff5d9ba45b24aeac71b94c6c621b78"

inherit cmake pkgconfig
EXTRA_OECMAKE += "-DBUILD_TESTING=OFF"
