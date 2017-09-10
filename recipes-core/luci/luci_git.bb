# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt LuCI web user interface"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"
SECTION = "base"
DEPENDS = "json-c libubox libnl lua5.1 iwinfo openssl"
RDEPENDS_${PN} = "lua5.1"

SRCREV = "d89b022b97b4516bef708212135780b818b9d4a2"

SRC_URI = "git://github.com/openwrt/luci.git;branch=for-15.05"
SRC_URI += "file://cmake.patch"

inherit cmake openwrt pkgconfig

prefix=""
includedir="/usr/include"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3"

FILES_${PN} += "/www /usr/lib"

S = "${WORKDIR}/git/"

