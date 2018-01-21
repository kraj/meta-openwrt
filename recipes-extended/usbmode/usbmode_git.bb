# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "usbmode - usb_modeswitch replacement"
HOMEPAGE = "http://git.openwrt.org/?p=project/usbmode.git;a=summary"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/PD;md5=b3597d12946881e13cb3b548d1173851"
SECTION = "base"
DEPENDS = "libusb libubox"

SRC_URI = "git://git.openwrt.org/project/usbmode.git"

SRCREV = "993a9a542791953c4804f7ddbb3a07756738e37a"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

FILES_${PN}  += "${libdir}/*"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug"
