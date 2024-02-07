# Copyright (C) 2020 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "This package contains a stripped down version of libnl"
HOMEPAGE = "https://git.openwrt.org/?p=project/libnl-tiny.git;a=summary"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://nl.c;startline=4;endline=7;md5=f16bd5d25e622bb3001bab76be1f9f91"
SECTION = "libs"

SRCREV = "8e0555fb39f51a5d6436b4f1370850caa03611ea"
SRC_URI = "git://git.openwrt.org/project/libnl-tiny.git;protocol=https;branch=master"

inherit cmake pkgconfig
S = "${WORKDIR}/git"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so"
