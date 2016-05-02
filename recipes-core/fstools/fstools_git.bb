# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt filesystem utilities"
HOMEPAGE = "https://git.openwrt.org/?p=project/fstools.git;a=summary"
# libubi is under GPL
# libblkid-tiny is PD
# libfstools is LGPL
LICENSE = "LGPL-2.1 & GPL-2.0 & PD"
LIC_FILES_CHKSUM = "file://ubi.c;beginline=1;endline=17;md5=8ccc371d64f0b3a8d91065b678dc7095"
SECTION = "base"

SRCREV = "96415afecef35766332067f4205ef3b2c7561d21"
SRC_URI = "git://git.openwrt.org/project/fstools.git \
           file://0001-Define-GLOB_ONLYDIR-if-not-available.patch \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "${EXTRA_OECONF}"

# avoids build breaks when using no-static-libs.inc
DISABLE_STATIC = ""

PACKAGECONFIG ??= "extroot"

PACKAGECONFIG[extroot] = "-DCMAKE_UBIFS_EXTROOT=ON,,libubox uci,"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
