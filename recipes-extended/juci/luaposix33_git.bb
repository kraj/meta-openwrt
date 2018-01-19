# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Lua bindings for POSIX"

DESCRIPTION = "A library binding various POSIX APIs.\
    POSIX is the IEEE Portable Operating System Interface standard.\
    luaposix is based on lposix.\
"
HOMEPAGE = "http://luaposix.github.io/luaposix/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=9bed33188dd18fa8fec97a710e234273"
SECTION = "libs"

SRCREV = "0e56d7ed756509eb6a4806bd31c088e14b74a853"
SRC_URI = "git://github.com/luaposix/luaposix"

S = "${WORKDIR}/git"

B = "${S}"

inherit openwrt

export LUA = "lua"

do_configure() {
	${S}/bootstrap
	./configure
}

do_install() {
	oe_runmake install
}

