DESCRIPTION = "Lua is a powerful light-weight programming language designed \
for extending applications."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=59bdd99bb82238f238cf5c65c21604fd"
HOMEPAGE = "http://www.lua.org/"

PR = "r2"

V = "5.1"

DEPENDS += "readline"
SRC_URI = "http://www.lua.org/ftp/lua-${PV}.tar.gz \
           file://${BPN}.pc \
           file://bitwise_operators.patch \
           file://luaorg_1.patch \
           file://luaorg_2.patch \
           file://0004-Fix-stack-overflow-in-vararg-functions.patch \
"
SRC_URI[md5sum] = "2e115fe26e435e33b0d5c022e4490567"
SRC_URI[sha256sum] = "2640fc56a795f29d28ef15e13c34a47e223960b0240e8cb0a82d9b0738695333"

S = "${WORKDIR}/lua-${PV}"

inherit pkgconfig binconfig

UCLIBC_PATCHES += "file://uclibc-pthread.patch"
SRC_URI_append_libc-uclibc = "${UCLIBC_PATCHES}"

TARGET_CC_ARCH += " -fPIC ${LDFLAGS}"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} -DLUA_USE_LINUX -fPIC' MYLDFLAGS='${LDFLAGS}'"

do_configure_prepend() {
    sed -i -e s:/usr/local:${prefix}:g src/luaconf.h
}

do_compile () {
    oe_runmake linux
}

do_install () {
    oe_runmake \
        'INSTALL_TOP=${D}${prefix}' \
        'INSTALL_BIN=${D}${bindir}' \
        'INSTALL_INC=${D}${includedir}/${BPN}' \
        'INSTALL_MAN=${D}${mandir}/man1' \
        'INSTALL_SHARE=${D}${datadir}/lua' \
        'INSTALL_LIB=${D}${libdir}' \
        'INSTALL_CMOD=${D}${libdir}/lua/${V}' \
        install
    mv ${D}${bindir}/lua ${D}${bindir}/lua${V}
    mv ${D}${bindir}/luac ${D}${bindir}/luac${V}
    mv ${D}${libdir}/liblua.a ${D}${libdir}/lib${BPN}.a
    install -D -m 644 ${WORKDIR}/${BPN}.pc ${D}${libdir}/pkgconfig/${BPN}.pc
    rmdir ${D}${datadir}/lua/${V}
    rmdir ${D}${datadir}/lua

}

FILES_${PN} += "${libdir}/lua ${libdir}/lua/${V}"

BBCLASSEXTEND = "native"

inherit update-alternatives

ALTERNATIVE_PRIORITY = "100"

ALTERNATIVE_${PN} = "lua luac"

ALTERNATIVE_TARGET[lua] = "${bindir}/lua${V}"
ALTERNATIVE_TARGET[luac] = "${bindir}/luac${V}"
