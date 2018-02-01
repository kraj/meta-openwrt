# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt JSON parsing utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/jsonpath.git;a=summary"
LICENSE = "BSD-1-Clause"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=15;md5=e1b007aed2273fc3ec1d16560a17aadc"
SECTION = "base"
DEPENDS = "json-c libubox"

SRC_URI = "git://git.openwrt.org/project/jsonpath.git;name=jsonpath; \
          file://0001-sync-lemon-parser.patch \
          file://0002-Declare-ParseTrace.patch \
          file://0100-break-lemon-dependency-cycle.patch \
          "

SRCREV_jsonpath = "dea067ad67d977c247c300c06676a06adf21e0c7"

inherit cmake pkgconfig

S = "${WORKDIR}/git"
B = "${S}"

do_install_append() {
    ln -s ${bindir}/jsonpath ${D}${bindir}/jsonfilter
}
