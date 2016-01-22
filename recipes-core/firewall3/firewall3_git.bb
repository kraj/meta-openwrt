# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt firewall configuration utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/firewall3.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=587cc5ad76f548db3e8674df450dc88f"
SECTION = "base"
DEPENDS = "libubox uci ubus iptables"

SRCREV = "18a503d0125aebc3a8d62dad1c02e6bb1da92eb6"
SRC_URI = "git://git.openwrt.org/project/firewall3.git \
           file://0001-Quickfix-for-iptables-2.6.x.patch \
          "

inherit cmake pkgconfig

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '-DDISABLE_IPV6=OFF', '-DDISABLE_IPV6=ON', d)}"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*"

