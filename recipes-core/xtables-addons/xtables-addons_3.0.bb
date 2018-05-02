SUMMARY = "non-mainline-kernel netfilter extensions"
DESCRIPTION = "Xtables-addons contains a set of possibly useful but not included in the mainline kernel nefilter extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "virtual/kernel iptables"

inherit autotools kernel-module-split module-base pkgconfig

SRC_URI = " \
          https://fossies.org/linux/privat/${BPN}-${PV}.tar.xz \
          file://100-add-rtsp-conntrack.patch \
          file://200-add-lua-packetscript.patch \
          file://201-fix-lua-packetscript.patch \
          file://202-add-lua-autoconf.patch \
          file://300-geoip-endian-detection.patch \
          file://400-fix-IFF_LOWER_UP-musl.patch \
          file://fix-to-build-linux-v4.15-and-later.patch \
          file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
          "

SRC_URI[md5sum] = "b37ed4d9c28cdcd5558c55934be8d051"
SRC_URI[sha256sum] = "95580b851c79c0bbc484e0d0ea23f53e5c7f439ad73d509e426598565392690d"

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
