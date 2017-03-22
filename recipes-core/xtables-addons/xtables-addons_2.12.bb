DESCRIPTION = "Xtables-addons contains a set of possibly useful but not included in the mainline kernel nefilter extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "virtual/kernel iptables"
RDEPENDS_${PN} += "perl"

inherit autotools module-base

SRC_URI = " \
        ${SOURCEFORGE_MIRROR}/project/${PN}/Xtables-addons/${PN}-${PV}.tar.xz \
        file://100-add-rtsp-conntrack.patch \
        file://200-add-lua-packetscript.patch \
        file://201-fix-lua-packetscript.patch \
        file://202-add-lua-autoconf.patch \
        file://300-geoip-endian-detection.patch \
        "

SRC_URI[md5sum] = "aed5ce0873709ac243f1177fc81ff452"
SRC_URI[sha256sum] = "c4865aa1c64c5ff173ff7b5d69425466c71f0f9b5eb5299c52c68bdcd46fa63b"

EXTRA_OECONF = "--with-kbuild=${STAGING_KERNEL_DIR} --with-xtlibdir=${libdir}/iptables"

addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[deptask] = "do_populate_sysroot"

FILES_${PN} += "${base_libdir}/modules ${libdir}/iptables"
