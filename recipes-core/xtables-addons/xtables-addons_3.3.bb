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


SRC_URI[md5sum] = "e99ea681b7b3866a581390e1b3ea185e"
SRC_URI[sha256sum] = "efa62c7df6cd3b82d7195105bf6fe177b605f91f3522e4114d2f4e0ad54320d6"

S = "${WORKDIR}/xtables-addons-${PV}"


MODULES_MODULE_SYMVERS_LOCATION = "../${BPN}-${PV}/extensions"

EXTRA_OECONF = "--with-kbuild=${STAGING_KERNEL_DIR}"

EXTRA_OEMAKE = "M=${S}/extentions DESTDIR=${D} V=1"
MODULES_INSTALL_TARGET = "install"
# make_scripts requires kernel source directory to create
# kernel scripts
do_make_scripts[depends] += "virtual/kernel:do_shared_workdir"

FILES_${PN} += "${libexecdir}/xtables-addons ${sbindir}/iptaccount ${libdir}/libxt_ACCOUNT_cl.so.* ${libdir}/iptables"

RDEPENDS_${PN} += "perl"
