OECMAKE_C_FLAGS += "-DLUA_COMPAT_5_1"
EXTRA_OECMAKE += "-DLUAPATH=/usr/lib/lua/5.1"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/* ${datadir}/lua/5.*/"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug"

DEPENDS += "lua5.1-native"
OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"
CFLAGS += "-I${STAGING_INCDIR}/lua5.1"

# Use this git SRCREV for all recipes that pull files out of openwrt repository
# Equivalent to tag v15.05.1
OPENWRT_SRCREV = "0c335d6ea00af9b32225f72d491e560c1a7cf4ab"
