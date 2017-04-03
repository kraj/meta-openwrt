# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt firewall configuration utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/firewall3.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2a8ffaa9ef41014f236ab859378e8900"
SECTION = "base"
DEPENDS = "libubox uci ubus iptables"
RDEPENDS_${PN} = "ipset xtables-addons"

SRCREV = "82ccd9e34fe87d31d9909fed754950b2c75bc6ac"
SRC_URI = "git://git.openwrt.org/project/firewall3.git \
           git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;protocol=git;branch=chaos_calmer \
          "

inherit cmake pkgconfig openwrt

SRCREV_openwrt = "${OPENWRT_SRCREV}"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '-DDISABLE_IPV6=OFF', '-DDISABLE_IPV6=ON', d)}"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/config
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc.d
    install -d ${D}${base_sbindir}

    install -m 0755 ${WORKDIR}/git/openwrt/package/network/config/firewall/files/firewall.init ${D}${sysconfdir}/init.d/firewall
    install -m 0644 ${WORKDIR}/git/openwrt/package/network/config/firewall/files/firewall.config ${D}${sysconfdir}/config/firewall
    install -m 0644 ${WORKDIR}/git/openwrt/package/network/config/firewall/files/firewall.user ${D}${sysconfdir}/firewall.user

    ln -s ${sbindir}/firewall3 ${D}${base_sbindir}/fw3

    ln -s ../init.d/firewall ${D}${sysconfdir}/rc.d/S19firewall
}
