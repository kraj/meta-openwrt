FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

EXTRA_OECONF += "--enable-shared --disable-static --with-xtlibdir=/usr/lib/iptables"

SRC_URI += "file://600-shared-libext.patch"

LDFLAGS += "-fuse-ld=bfd"

do_install_append() {
	install -d ${D}${libdir}
	install -m 0644 ${B}/extensions/libiptext*.so ${D}${libdir}
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"
