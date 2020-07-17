SUMMARY = "non-mainline-kernel netfilter extensions"
DESCRIPTION = "Xtables-addons contains a set of possibly useful but not included in the mainline kernel nefilter extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "virtual/kernel iptables"

inherit autotools kernel-module-split module-base pkgconfig

SRC_URI = " \
          https://master.dl.sourceforge.net/project/xtables-addons/Xtables-addons/${BPN}-${PV}.tar.xz \
          file://001-fix-kernel-version-detection.patch \
          file://100-add-rtsp-conntrack.patch \
          file://200-add-lua-packetscript.patch \
          file://201-fix-lua-packetscript.patch \
          file://202-add-lua-autoconf.patch \
          file://400-fix-IFF_LOWER_UP-musl.patch \
          file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
          "


SRC_URI[md5sum] = "ebb073119a5f250dbfe6b855fcad56fd"
SRC_URI[sha256sum] = "d215a9a8b8e66aae04b982fa2e1228e8a71e7dfe42320df99e34e5000cbdf152"

S = "${WORKDIR}/xtables-addons-${PV}"

MODULES_MODULE_SYMVERS_LOCATION = "../${BPN}-${PV}/extensions"

BROKEN_PATHS = "-I${S}/extensions/"
BROKEN_PATHS += "-I${S}/extensions/LUA/lua/"
BROKEN_PATHS += "-I${S}/extensions/LUA/lua/include/"

GCC9_FAILURES = "-Wno-missing-attributes"
GCC9_FAILURES += "-Wno-bool-operation"
GCC9_FAILURES += "-Wno-logical-not-parentheses"
GCC9_FAILURES += "-Wno-misleading-indentation"


EXTRA_OECONF = "--with-kbuild=${STAGING_KERNEL_DIR} --with-xtlibdir=/usr/lib/iptables"

EXTRA_OEMAKE += "ccflags-y='${BROKEN_PATHS} ${GCC9_FAILURES}' M=${S}/extentions DESTDIR=${D} V=1"
MODULES_INSTALL_TARGET = "install"
# make_scripts requires kernel source directory to create
# kernel scripts
do_make_scripts[depends] += "virtual/kernel:do_shared_workdir"

FILES_${PN} += "${libexecdir}/xtables-addons ${sbindir}/iptaccount ${libdir}/libxt_ACCOUNT_cl.so.* ${libdir}/iptables"