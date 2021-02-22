# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt LuCI web user interface"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"
SECTION = "base"
DEPENDS = "json-c libubox libnl lua5.1 iwinfo openssl libxcrypt"
RDEPENDS_${PN} = "lua5.1"

SRCREV = "8674e2a004c4407b3a454cf819f4d0308e0c02df"

SRC_URI = "git://github.com/openwrt/luci.git;branch=openwrt-19.07 \
           file://cmake.patch \
           file://plural_formula.c \
           file://plural_formula.h \
           "

inherit cmake openwrt pkgconfig

RDEPENDS_${PN} += " liblucihttp"

prefix=""
includedir="/usr/include"
bindir="/usr/bin"
libdir="/usr/${baselib}"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -DDESTDIR=${D}"

S = "${WORKDIR}/git"

do_configure_prepend() {
    cp ${WORKDIR}/plural_formula.* ${S}/modules/luci-base/src/
}

FILES_${PN} += "/www ${base_libdir} /usr/share /usr/libexec"
