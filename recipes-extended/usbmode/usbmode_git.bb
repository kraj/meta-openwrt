# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "usbmode - usb_modeswitch replacement"
HOMEPAGE = "http://git.openwrt.org/?p=project/usbmode.git;a=summary"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/PD;md5=b3597d12946881e13cb3b548d1173851"
SECTION = "base"
DEPENDS = "libusb libubox"

SRC_URI = "git://git.openwrt.org/project/usbmode.git"

SRCREV = "f40f84c27534159066c94dadc0c08e0b255c3e26"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

FILES:${PN}  += "${libdir}/*"
FILES:${PN}-dbg  += "${libdir}/lua/5.*/.debug"
