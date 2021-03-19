# SPDX-FileCopyrightText: 2021 Bosch Sicherheitssysteme GmbH
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "LuCI HTTP utility library"
HOMEPAGE = "https://github.com/jow-/lucihttp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6b7565d075eb26cd08b6ac739db35e3"

DEPENDS_class-target += " lua5.1"

SRCREV = "a34a17d501c0e23f0a91dd9d3e87697347c861ba"
SRC_URI = "git://github.com/jow-/lucihttp.git"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-lua

# Do not build tests
EXTRA_OECMAKE += "-DBUILD_TESTS=OFF"

RDEPENDS_${PN} = "lua5.1"
