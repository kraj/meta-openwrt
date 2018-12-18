# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

XTLIBDIR = "${libdir}/iptables"

EXTRA_OECONF += "--enable-shared --disable-static --with-xtlibdir=${XTLIBDIR}"

SRC_URI += "file://600-shared-libext.patch"

LDFLAGS += "-fuse-ld=bfd"

python populate_packages_prepend() {
    modules = do_split_packages(d, '${XTLIBDIR}', 'lib(.*)\.so$', '${PN}-module-%s', '${PN} module %s', extra_depends='', prepend=True)
    if modules:
        metapkg = d.getVar('PN') + '-modules'
        d.appendVar('RDEPENDS_' + metapkg, ' ' + ' '.join(modules))
}

do_install_append() {
	install -d ${D}${libdir}
	install -m 0644 ${B}/extensions/libiptext*.so ${D}${libdir}
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"


