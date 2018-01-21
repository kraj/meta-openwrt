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
DEPENDS += "util-linux"

SRC_URI = "git://git.openwrt.org/project/fstools.git;branch=lede-17.01 \
	   file://0001-Define-GLOB_ONLYDIR-if-not-available.patch \
          "

SRCREV = "37762ff488752c1686b359816aec75831b49c55f"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

EXTRA_OECMAKE += "${EXTRA_OECONF}"

# avoids build breaks when using no-static-libs.inc
DISABLE_STATIC = ""

PACKAGECONFIG ??= "extroot"

PACKAGECONFIG[extroot] = "-DCMAKE_UBIFS_EXTROOT=ON,,libubox uci,"

do_install_append() {
	install -dm 0755 ${D}/sbin
	ln -s /usr/sbin/mount_root ${D}/sbin/mount_root
	ln -s /usr/sbin/jffs2reset ${D}/sbin/jffs2reset
}

FILES_${PN}  += "${libdir}/*"

