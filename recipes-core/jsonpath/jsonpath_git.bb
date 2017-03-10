# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt JSON parsing utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/jsonpath.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=15;md5=60fb698e1ae531930b377e9f77d3ba1f"
SECTION = "base"
DEPENDS = "json-c libubox"

SRCREV = "cdc760c58077f44fc40adbbe41e1556a67c1b9a9"
SRC_URI = "git://git.openwrt.org/project/jsonpath.git \
           file://0001-sync-lemon-parser.patch \
           file://0002-Declare-ParseTrace.patch \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

B = "${S}"

do_install_append() {
    ln -s ${bindir}/jsonpath ${D}${bindir}/jsonfilter
}
