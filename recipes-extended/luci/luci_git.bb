# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt LuCI web user interface"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"
SECTION = "base"

DEPENDS = "json-c libubox libnl lua5.1 iwinfo openssl virtual/crypt"
RDEPENDS_${PN} = "lua5.1 lucihttp"

SRCREV = "07d9006d239b29c9377fccb91b45eb2d0e447919"

SRC_URI = "git://github.com/openwrt/luci.git \
           file://cmake.patch"

inherit cmake openwrt pkgconfig

prefix=""
includedir="/usr/include"
bindir="/usr/bin"
libdir="/usr/${baselib}"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -DDESTDIR=${D}"

S = "${WORKDIR}/git"

FILES_${PN} += "/www ${base_libdir} /usr/share /usr/libexec"
