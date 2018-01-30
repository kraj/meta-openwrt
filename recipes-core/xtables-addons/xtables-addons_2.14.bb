SUMMARY = "non-mainline-kernel netfilter extensions"
DESCRIPTION = "Xtables-addons contains a set of possibly useful but not included in the mainline kernel nefilter extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "virtual/kernel iptables"

inherit autotools module-base pkgconfig

SRC_URI = " \
          ${SOURCEFORGE_MIRROR}/project/${PN}/Xtables-addons/${PN}-${PV}.tar.xz \
          file://100-add-rtsp-conntrack.patch \
          file://200-add-lua-packetscript.patch \
          file://201-fix-lua-packetscript.patch \
          file://202-add-lua-autoconf.patch \
          file://300-geoip-endian-detection.patch \
          file://400-fix-IFF_LOWER_UP-musl.patch \
          "

SRC_URI[md5sum] = "ebb073119a5f250dbfe6b855fcad56fd"
SRC_URI[sha256sum] = "d215a9a8b8e66aae04b982fa2e1228e8a71e7dfe42320df99e34e5000cbdf152"

EXTRA_OECONF = "--with-kbuild=${STAGING_KERNEL_DIR} --with-xtlibdir=${libdir}/iptables"

addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[deptask] = "do_populate_sysroot"

FILES_${PN} += "${base_libdir}/modules ${libdir}/iptables"

RDEPENDS_${PN} += "perl"
