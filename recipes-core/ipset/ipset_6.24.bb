# Copyright (C) 2017 Aaron Brice <aaron.brice@datasoft.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Administration tool for IP sets"
HOMEPAGE = "http://ipset.netfilter.org"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
SECTION = "base"

inherit autotools pkgconfig

SRC_URI = "http://ipset.netfilter.org/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "8831b8f01458bf2abacc222884195a62"
SRC_URI[sha256sum] = "3071fc283f00a6472b5b352ef57f9825c9face70dda5b0d8715f8d43d0e995d0"

DEPENDS += "libtool libmnl"
