DESCRIPTION = "Networking extension library for Lua"
HOMEPAGE = "https://github.com/diegonehab/luasocket"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab6706baf6d39a6b0fa2613a3b0831e7"

DEPENDS += "lua5.1-native lua5.1"

SRCREV = "d1ec29be7f982db75864155dd61a058902e1cae2"
SRC_URI = "git://github.com/diegonehab/luasocket.git \
           file://0001-allow-overrides-for-DESTDIR-CC_linux-LD_linux-LDFLAG.patch \
          "

TARGET_CC_ARCH += "${LDFLAGS}"

S = "${WORKDIR}/git"

CFLAGS += "-I${STAGING_INCDIR}/lua5.1"
EXTRA_OEMAKE += "PLAT=linux LUAINC_linux_base=${STAGING_INCDIR} LUAPREFIX_linux=${prefix} DESTDIR=${D} \
CC_linux='${CC}' LD_linux='${CC}' LUAV=5.1"

do_install() {
	oe_runmake install
}

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/* ${datadir}/lua/5.*/"
FILES_${PN}-dbg  += "${libdir}/lua/5.*/.debug ${libdir}/lua/5.*/*/.debug"

