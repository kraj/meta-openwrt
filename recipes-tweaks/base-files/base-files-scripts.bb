# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Released under the MIT license.  See COPYING.MIT for terms

inherit openwrt openwrt-base-files

DESCRIPTION = "Subpackages from base-files from OpenWrt core"
HOMEPAGE = "http://wiki.openwrt.org/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=a8db84c7a073d2878849eee8eb0f5daa"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-scripts:"

SRC_URI += "git://github.com/openwrt/openwrt.git;protocol=https;branch=openwrt-22.03 \
           "

SRCREV = "${OPENWRT_SRCREV}"

S = "${WORKDIR}/git"
SC = "${WORKDIR}/git/package/base-files/files"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

PACKAGES = "\
           ${PN}-openwrt \
           ${PN}-sysupgrade \
           "

do_install:append () {
    mkdir -p ${D}${sysconfdir}/config

    install -Dm 0644 ${SC}/lib/functions.sh ${D}/lib/functions.sh
    install -Dm 0644 ${SC}/lib/functions/uci-defaults.sh ${D}/lib/functions/uci-defaults.sh
    install -Dm 0644 ${SC}/lib/functions/system.sh ${D}/lib/functions/system.sh
    install -Dm 0755 ${SC}/bin/ipcalc.sh ${D}/bin/ipcalc.sh
    install -Dm 0644 ${SC}/etc/sysupgrade.conf ${D}/etc/sysupgrade.conf
    install -Dm 0755 ${SC}/sbin/sysupgrade ${D}/sbin/sysupgrade
    install -Dm 0755 ${SC}/sbin/firstboot ${D}/sbin/firstboot

    install -dm 0755 ${D}/lib
    cp -dR --preserve=mode,links ${SC}/lib/upgrade ${D}/lib
}

FILES:${PN}-openwrt = "\
                      /lib/functions.sh \
                      /lib/functions/uci-defaults.sh \
                      /lib/functions/system.sh \
                      /bin/ipcalc.sh \
                      ${sysconfdir}/config \
                      "

FILES:${PN}-sysupgrade = "\
                         /etc/sysupgrade.conf \
                         /sbin/sysupgrade \
                         /lib/upgrade/* \
                         /sbin/firstboot \
                         "

CONFFILES:${PN}-openwrt += "\
                           ${sysconfdir}/config \
                           "

CONFFILES:${PN}-sysupgrade += "\
                              ${sysconfdir]/sysupgrade.conf \
                              "

PACKAGE_ARCH = "${MACHINE_ARCH}"
