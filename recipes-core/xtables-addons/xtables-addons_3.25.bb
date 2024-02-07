SUMMARY = "non-mainline-kernel netfilter extensions"
DESCRIPTION = "Xtables-addons contains a set of possibly useful but not included in the mainline kernel nefilter extensions"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "virtual/kernel iptables"

inherit autotools kernel-module-split module-base pkgconfig

CFLAGS:append:toolchain-clang = " -Wno-error=implicit-function-declaration"

SRC_URI = " \
          https://inai.de/files/xtables-addons/${BP}.tar.xz \
          file://001-fix-kernel-version-detection.patch \
          file://100-add-rtsp-conntrack.patch \
          file://200-add-lua-packetscript.patch \
          file://201-fix-lua-packetscript.patch \
          file://202-add-lua-autoconf.patch \
          file://400-fix-IFF_LOWER_UP-musl.patch \
          file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
          "
SRC_URI[sha256sum] = "8c9f4c6a8e92eb7cfbf03f4ebcb1e1e793256c2efd0226d83312bfb0ffe14b84"

MODULES_MODULE_SYMVERS_LOCATION = "../${BP}/extensions"

EXTRA_OECONF = "--with-kbuild=${STAGING_KERNEL_DIR}"

do_compile:prepend () {
    # install additional empty wrappers
    touch ${S}/extensions/LUA/lua/include/stdarg.h
    touch ${S}/extensions/LUA/lua/include/stddef.h
}

EXTRA_OEMAKE = "M=${S}/extentions DESTDIR=${D} V=1"
MODULES_INSTALL_TARGET = "install"
# make_scripts requires kernel source directory to create
# kernel scripts
do_make_scripts[depends] += "virtual/kernel:do_shared_workdir"

FILES:${PN} += "${libexecdir}/xtables-addons ${sbindir}/iptaccount ${libdir}/libxt_ACCOUNT_cl.so.* ${libdir}/iptables"

RDEPENDS:${PN} += "perl"
