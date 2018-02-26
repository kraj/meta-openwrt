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

SRC_URI[md5sum] = "b37ed4d9c28cdcd5558c55934be8d051"
SRC_URI[sha256sum] = "95580b851c79c0bbc484e0d0ea23f53e5c7f439ad73d509e426598565392690d"

EXTRA_OECONF = "--with-kbuild=${STAGING_KERNEL_DIR} --with-xtlibdir=${libdir}/iptables"

addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[deptask] = "do_populate_sysroot"

do_make_scripts() {
	:
}

FILES_${PN} += "${base_libdir}/modules ${libdir}/iptables"

RDEPENDS_${PN} += "perl"
