# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "OpenWrt system message/RPC bus"
HOMEPAGE = "http://git.openwrt.org/?p=project/libubox.git;a=summary"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"
SECTION = "base"
DEPENDS = "json-c libubox lua5.1"

SRCREV_luci = "5b79e62c0a99bab8dfb8dce8124d9fecc11da54b"
SRCREV_openwrt = "ef1e28c670cdbc96f2dd961eb68c07eead73d7de"

SRC_URI = "git://git.openwrt.org/project/luci.git;name=luci \
           git://git.openwrt.org/openwrt.git;name=openwrt;destsuffix=git/openwrt/ \
"

inherit pkgconfig openwrt

S = "${WORKDIR}/git"
EXTRA_OEMAKE += "TOPDIR=${S}/openwrt"

do_compile () {
	cd ${S}/openwrt
	./scripts/feeds update –a
        ./scripts/feeds install –a

	for d in modules/luci-base
	do
		cd ${S}/$d
		oe_runmake
	done
}
