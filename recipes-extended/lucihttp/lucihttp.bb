# SPDX-FileCopyrightText: 2021 Bosch Sicherheitssysteme GmbH
#
# SPDX-License-Identifier: MIT

DESCRIPTION = "LuCI HTTP utility library"
HOMEPAGE = "https://github.com/jow-/lucihttp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6b7565d075eb26cd08b6ac739db35e3"

DEPENDS:class-target += " lua5.1 ucode"

SRCREV = "6e68a1065f3ed1889e5fa053b206bd3aa108bd5f"
SRC_URI = "git://github.com/jow-/lucihttp.git;branch=master;protocol=https"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-lua

# Do not build tests
EXTRA_OECMAKE += "-DBUILD_TESTS=OFF"

RDEPENDS:${PN} = "lua5.1 ucode"
