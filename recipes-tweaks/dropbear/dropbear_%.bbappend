FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://dropbear.config \
    file://dropbear.init \
"

do_install_append() {
    install -d ${D}${sysconfdir}/config
    install -m 0644 ${WORKDIR}/dropbear.config ${D}${sysconfdir}/config/dropbear

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/dropbear.init ${D}${sysconfdir}/init.d/dropbear

    # dropbear installs in /usr/sbin, openwrt looks for it in /usr/bin
    ln -s ${sbindir}/dropbearmulti ${D}${bindir}/dropbearmulti
    ln -s ${sbindir}/dropbearkey ${D}${bindir}/dropbearkey
    ln -s ${sbindir}/dropbearconvert ${D}${bindir}/dropbearconvert
    ln -s ${sbindir}/dropbear ${D}${bindir}/dropbear
}

INITSCRIPT_NAME = "dropbear"
INITSCRIPT_PARAMS = "defaults 19 50"
