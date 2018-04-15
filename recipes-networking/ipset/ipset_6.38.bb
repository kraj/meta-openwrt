# Copyright (C) 2017 Aaron Brice <aaron.brice@datasoft.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Administration tool for IP sets"
HOMEPAGE = "http://ipset.netfilter.org"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
SECTION = "base"

DEPENDS = "libtool libmnl"
RDEPENDS_${PN} = "kernel-module-ip-set"

SRC_URI = "http://ftp.netfilter.org/pub/ipset/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "0e5d9c85f6b78e7dff0c996e2900574b"
SRC_URI[sha256sum] = "ceef625ba31fe0aaa422926c7231a819de0b07644c02c17ebdd3022a29e3e244"

inherit autotools pkgconfig module-base

EXTRA_OECONF += "-with-kbuild=${KBUILD_OUTPUT} --with-ksource=${STAGING_KERNEL_DIR}"
