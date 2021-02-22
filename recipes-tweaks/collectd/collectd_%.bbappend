FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-Patches-from-OpenWRT.patch \
    file://collectd.init \
    file://collectd.config \
    file://plugin \
"

inherit perlnative

DEPENDS += " iwinfo"

RDEPENDS_${PN} += " iwinfo"

do_install_append() {
    install -d ${D}/usr/share/plugin
    cp -rdR --preserve=mode,links ${WORKDIR}/plugin ${D}/usr/share/

    install -d ${D}${sysconfdir}/config/
    install -m 0644 ${WORKDIR}/collectd.config ${D}${sysconfdir}/config/collectd

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/collectd.init ${D}${sysconfdir}/init.d/collectd
}

FILES_${PN} += " \
    /usr/share/plugin/ \
"
