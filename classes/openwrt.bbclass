# This file Copyright (C) 2015 Khem Raj <raj.kem@gmail.com> and
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

OECMAKE_C_FLAGS += "-DLUA_COMPAT_5_1"
EXTRA_OECMAKE += "-DLUAPATH=/usr/lib/lua/5.1"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/* ${datadir}/lua/5.*/"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug"

DEPENDS += "lua5.1-native"
OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"
CFLAGS += "-I${STAGING_INCDIR}/lua5.1"

inherit openwrt-virtual-runtimes
