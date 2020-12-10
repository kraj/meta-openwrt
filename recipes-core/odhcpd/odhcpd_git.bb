# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "OpenWrt DHCP/DHCPv6(-PD)/RA Server & Relay"
HOMEPAGE = "http://git.openwrt.org/?p=project/odhcpd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://src/odhcpd.c;beginline=1;endline=13;md5=b5b1da01ca7e1cdd0308c552dc0a1384"
SECTION = "base"
DEPENDS = "libubox ubus libnl-tiny uci"

FILESEXTRAPATHS_prepend = "${THIDIR}/${PN}:"

SRC_URI = "\
    git://git.openwrt.org/project/odhcpd.git;name=odhcpd \
"

SRCREV_odhcpd = "8f27697b9b82420890cedd429622052c8b67cea1"

S = "${WORKDIR}/git"
OF = "${S}/openwrt/package/network/services/odhcpd/files"

inherit cmake pkgconfig openwrt openwrt-services openwrt-base-files

SRCREV_openwrt = "${OPENWRT_SRCREV}"

EXTRA_OECMAKE_append = " -DUBUS=1"

do_install_append() {
    install -Dm 0644 ${OF}/odhcpd.defaults ${D}${sysconfdir}/uci-defaults/odhcpd
    install -Dm 0755 ${OF}/odhcpd.init ${D}${sysconfdir}/init.d/odhcpd
    install -Dm 0755 ${OF}/odhcpd-update ${D}${sbindir}/odhcpd-update
}

FILES_${PN} += "\
               ${sysconfdir}/uci-defaults/odhcpd.defaults \
               ${sysconfdir}/init.d/odhcpd \
               ${sbindir}/odhcpd-update \
               "

RDEPENDS_${PN} += "base-files-scripts-openwrt"
