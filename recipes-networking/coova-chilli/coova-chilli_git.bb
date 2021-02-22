SUMMARY = "Open-source software access controller"
DESCRIPTION = "CoovaChilli is a feature rich software access controller that \
provides a captive portal / walled-garden environment and uses RADIUS or a \
HTTP protocol for access provisioning and accounting."
HOMEPAGE = "https://coova.github.io/CoovaChilli/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=781455c2e21bce5ab467c7dd0157206e"

SRC_URI = " \
    git://github.com/coova/coova-chilli.git;protocol=https;branch=master \
    file://0001-change-shebang-in-python-script.patch \
    file://001-chilli.in-add-needed-killproc-function.patch \
    file://001-redir.patch \
    file://chilli.init \
    "
SRCREV = "08b48d908a1284a46d880e80fb0533d9f998e3fc"

S = "${WORKDIR}/git"

DEPENDS += " \
    gengetopt-native \
    python-native \
    "

RDEPENDS_${PN} = " \
    freeradius \
    haserl \
    daemon \
    cronie \
    iptables \
    "

EXTRA_OECONF += " \
    --without-cyassl \
    --without-openssl \
    "

# related to this issue https://github.com/coova/coova-chilli/issues/501
CFLAGS_append = "-Wno-error=stringop-truncation -Wno-error=format-truncation -Wno-error=address-of-packed-member"

inherit autotools-brokensep pkgconfig

# Create directory needed to start coova-chilli
do_install_append() {
    install -d ${D}/run/lock/subsys
    install -d ${D}${sysconfdir}/init.d

    install -m 0755 ${WORKDIR}/chilli.init ${D}${sysconfdir}/init.d/chilli
}

FILES_${PN} += " \
    ${libdir}/python/CoovaChilliLib.py \
    /run/lock/subsys \
    "
