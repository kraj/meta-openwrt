# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt automount daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/mountd.git;a=summary"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://uci.c;beginline=1;endline=18;md5=fe0ec3006d61d1ac4e74c21e0a2726c5"
SECTION = "base"
DEPENDS = "libubox uci virtual/kernel"

SRC_URI = "\
          git://git.openwrt.org/project/mountd.git;name=mountd \
          file://0100-prevent-stddef-redefinition.patch \
          file://0200-prevent-musl-endian-h-parenthese-warning.patch \
          "

SRCREV_mountd = "7826ca5d6aca691dcb6f98ab203a090d42e79337"

inherit cmake pkgconfig openwrt openwrt-services openwrt-base-files

SRCREV_openwrt = "${OPENWRT_SRCREV}"

S = "${WORKDIR}/git"

do_configure_prepend () {
    sed -i "s:-Werror --std=gnu99:-Werror -Wno-format-truncation -Wno-format-overflow --std=gnu99:g" ${S}/CMakeLists.txt
}

do_install_append() {
    install -Dm 0755 ${S}/openwrt/package/system/mountd/files/mountd.config ${D}${sysconfdir}/config/mountd
    install -Dm 0755 ${S}/openwrt/package/system/mountd/files/mountd.init ${D}${sysconfdir}/init.d/mountd
    install -dm 0755 ${D}/sbin
    ln -sf /usr/sbin/mountd ${D}/sbin/mountd
}

FILES_${PN}  += "${libdir}/*"

RRDEPENDS_${PN} = "kernel-module-fs-auto_fs4"

