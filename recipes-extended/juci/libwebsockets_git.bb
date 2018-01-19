# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "lightweight C websockets library"
HOMEPAGE = "https://github.com/mkschreder/libblobpack"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f197d69f6bda1c450e2173a161286269"
SECTION = "libs"

DEPENDS += "zlib openssl"

SRCREV = "d459a6fadc7127f85c3bf46bf2b982199e68d023"
SRC_URI = "git://github.com/warmcat/libwebsockets"

S = "${WORKDIR}/git"
inherit cmake
PACKAGES += "${PN}-test"
FILES_${PN}-dev += "${libdir}/cmake"
FILES_${PN}-test += "${datadir}/"

