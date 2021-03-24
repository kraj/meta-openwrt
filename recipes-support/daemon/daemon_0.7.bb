SUMMARY = "Daemon turns other process into daemons"
SECTION = "support"
HOMEPAGE = "http://www.libslack.org/daemon/"
LICENSE = "LGPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=393a5ca445f6965873eca0259a17f833"
SRC_URI = " \
    http://www.libslack.org/daemon/download/daemon-${PV}.tar.gz \
    file://0001-remove-redundant-pod2man-marker.patch \
    "

SRC_URI[sha256sum] = "f66af2ece784c16dcb5219de1f4fa3ae5787bb3374e44bd4b1d3e275e8ff272c"

inherit perlnative

RDEPENDS_${PN} = " \
    bash \
    "

EXTRA_OEMAKE = " \
    'PREFIX=${D}${prefix}' \
    'DESTDIR=${D}' \
    'DAEMON_CONF_INSDIR=${D}${sysconfdir}' \
    "
INSANE_SKIP_${PN}_append = "already-stripped"

do_configure(){
    ${S}/configure
}

do_compile(){
    oe_runmake
}

do_install(){
    oe_runmake install
    oe_runmake DESTDIR=${D} install-daemon-conf
    oe_runmake install-slack
}
