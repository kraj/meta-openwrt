# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt filesystem utilities"
HOMEPAGE = "https://git.openwrt.org/?p=project/fstools.git;a=summary"
# libubi is under GPL
# libblkid-tiny is PD
# libfstools is LGPL
LICENSE = "LGPL-2.1-only & GPL-2.0-only & PD"
LIC_FILES_CHKSUM = "file://ubi.c;beginline=1;endline=17;md5=8ccc371d64f0b3a8d91065b678dc7095"
SECTION = "base"
DEPENDS += "util-linux ubus"

SRC_URI = "git://git.openwrt.org/project/fstools.git;protocol=https;branch=master \
           file://0001-Define-GLOB_ONLYDIR-if-not-available.patch \
          "

SRCREV = "8c213b0416b1a9411725f3c138d6c8b60032fd2b"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

EXTRA_OECMAKE += "${EXTRA_OECONF}"

# avoids build breaks when using no-static-libs.inc
DISABLE_STATIC = ""

PACKAGECONFIG ??= "extroot"

PACKAGECONFIG[extroot] = "-DCMAKE_UBIFS_EXTROOT=ON,,libubox uci,"

do_install:append() {
	install -dm 0755 ${D}/sbin
	ln -s /usr/sbin/mount_root ${D}/sbin/mount_root
	ln -s /usr/sbin/jffs2reset ${D}/sbin/jffs2reset
}

FILES:${PN}  += "${libdir}/*"
