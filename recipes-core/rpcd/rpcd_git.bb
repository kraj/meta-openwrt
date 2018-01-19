# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt UBUS RPC server"
HOMEPAGE = "http://git.openwrt.org/?p=project/rpcd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=da5faf55ed0618f0dde1c88e76a0fc74"
SECTION = "base"
DEPENDS = "json-c libubox ubus uci iwinfo"
RDEPENDS_${PN} += "iwinfo"

inherit cmake pkgconfig openwrt-services openwrt

SRCREV_pn-rpcd = "cfe1e75c91bc1bac82e6caab3e652b0ebee59524"
SRCREV_openwrt = "${OPENWRT_SRCREV}"

SRC_URI = "\
	git://git.openwrt.org/project/rpcd.git \
	git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;branch=lede-17.01 \
	"

S = "${WORKDIR}/git"
OR = "${S}/openwrt/package/system/rpcd/files"

do_install_append() {
    install -d ${D}${includedir}/rpcd
    install -m 0644 ${S}/include/rpcd/* ${D}${includedir}/rpcd/
    install -Dm 0755 ${OR}/rpcd.config ${D}${sysconfdir}/config/rpcd
    install -Dm 0755 ${OR}/rpcd.init ${D}${sysconfdir}/init.d/rpcd

    mkdir -p ${D}/sbin
    ln -s /usr/sbin/rpcd ${D}/sbin/rpcd
}

FILES_${PN}  += "${libdir}/*"
