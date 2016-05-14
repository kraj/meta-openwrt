# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Revolution RPC Server (RevoRPCD)"
HOMEPAGE = "https://github.com/mkschreder/jucid"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=ecb319022da02987a5c1a92120412393"
SECTION = "apps"

SRCREV = "b787f202a695eaba1fcc0feac1ff796fc37d4a43"
SRC_URI = "git://github.com/mkschreder/jucid \
           file://0001-GLOB_TILDE-is-not-defined-in-posix-therefore-not-imp.patch \
           file://0001-juci_ws_server.c-ubus_srv_ws_client_new-expects-no-p.patch \
           "

S = "${WORKDIR}/git"

inherit autotools pkgconfig
DEPENDS += "libblobpack libutype libusys luci uci lua5.1 libwebsockets iwinfo rpcd ubus"
RDEPENDS_${PN} += "libutype libblobpack libusys"
