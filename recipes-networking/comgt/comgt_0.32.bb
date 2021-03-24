LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://gpl.txt;md5=393a5ca445f6965873eca0259a17f833"
SECTION = "networking"

SRC_URI = " \
    https://sourceforge.net/projects/comgt/files/comgt/${PV}/${BPN}.${PV}.tgz \
    file://001-compile_fix.patch \
    file://002-termios.patch \
    file://003-no_XCASE.patch \
    file://004-check_tty.patch \
    file://files\
    "
SRC_URI[sha256sum] = "0cedb2a5aa608510da66a99aab74df3db363df495032e57e791a2ff55f1d7913"

S = "${WORKDIR}/${PN}.${PV}"

CFLAGS_append = "-Wno-error=format-security"
EXTRA_OEMAKE = " \
    'CC=${CC}' \
    'CFLAGS=${CFLAGS}' \
    'LDFLAGS=${LDFLAGS}' \
    'DESTDIR=${D}' \
    "

do_compile() {
    oe_runmake comgt
}

do_install() {
    install -d -m 755 ${D}/usr/bin
	install -m 755 ${S}/comgt ${D}/usr/bin/
	ln -sf comgt ${D}/usr/bin/gcom

    install -d -m 755 ${D}${sysconfdir}/gcom
	install -m 644 ${WORKDIR}/files/setpin.gcom ${D}${sysconfdir}/gcom/setpin.gcom
	install -m 644 ${WORKDIR}/files/setmode.gcom ${D}${sysconfdir}/gcom/setmode.gcom
	install -m 644 ${WORKDIR}/files/getcardinfo.gcom ${D}${sysconfdir}/gcom/getcardinfo.gcom
	install -m 644 ${WORKDIR}/files/getstrength.gcom ${D}${sysconfdir}/gcom/getstrength.gcom
	install -m 644 ${WORKDIR}/files/getcarrier.gcom ${D}${sysconfdir}/gcom/getcarrier.gcom
	install -m 644 ${WORKDIR}/files/getcnum.gcom ${D}${sysconfdir}/gcom/getcnum.gcom
	install -m 644 ${WORKDIR}/files/getimsi.gcom ${D}${sysconfdir}/gcom/getimsi.gcom
}
FILES_${PN} += " \
    ${sysconfdir}/gcom/ \
    "
