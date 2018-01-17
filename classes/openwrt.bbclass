OECMAKE_C_FLAGS += "-DLUA_COMPAT_5_1"
EXTRA_OECMAKE += "-DLUAPATH=/usr/lib/lua/5.1"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/* ${datadir}/lua/5.*/"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug"

DEPENDS += "lua5.1-native"
OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"
CFLAGS += "-I${STAGING_INCDIR}/lua5.1"

# Use this git SRCREV for all recipes that pull files out of openwrt repository
# Equivalent to tag v17.01.4
OPENWRT_SRCREV = "444add156f2a6d92fc15005c5ade2208a978966c"

inherit openwrt-virtual-runtimes
