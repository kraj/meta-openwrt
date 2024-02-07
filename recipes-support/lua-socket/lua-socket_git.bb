# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>

# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Networking extension library for Lua"
HOMEPAGE = "https://github.com/diegonehab/luasocket"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab6706baf6d39a6b0fa2613a3b0831e7"

DEPENDS += "lua5.1-native lua5.1"

SRC_URI = "git://github.com/diegonehab/luasocket.git;protocol=https;branch=master \
          "

SRCREV = "5b18e475f38fcf28429b1cc4b17baee3b9793a62"

S = "${WORKDIR}/git"

inherit openwrt

TARGET_CC_ARCH += "${LDFLAGS}"

CFLAGS += "-I${STAGING_INCDIR}/lua5.1"
EXTRA_OEMAKE += "PLAT=linux LUAINC_linux_base=${STAGING_INCDIR} LUAPREFIX_linux=${prefix} DESTDIR=${D} \
CC_linux='${CC}' LD_linux='${CC}' LUAV=5.1"

do_install() {
	oe_runmake install
}

FILES:${PN}  += "${libdir}/* ${datadir}/lua/5.*/"
FILES:${PN}-dbg  += "${libdir}/lua/5.*/.debug ${libdir}/lua/5.*/*/.debug"
