# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt automount daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/mountd.git;a=summary"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://uci.c;beginline=1;endline=18;md5=fe0ec3006d61d1ac4e74c21e0a2726c5"
SECTION = "base"
DEPENDS = "libubox uci"

SRCREV = "8476a03b25d457e99f59e6372b8d9faebe2266f8"
SRC_URI = "git://git.openwrt.org/project/mountd.git \
           file://mountd.config \
           file://mountd.init \
"

inherit cmake pkgconfig

S = "${WORKDIR}/git"

do_install_append() {
    install -Dm 0755 ${WORKDIR}/mountd.config ${D}${sysconfdir}/config/mountd
    install -Dm 0755 ${WORKDIR}/mountd.init ${D}${sysconfdir}/init.d/mountd
}

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"
