inherit openwrt

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;protocol=git;branch=chaos_calmer"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/config
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc.d

    install -m 0755 ${WORKDIR}/git/openwrt/package/network/services/dnsmasq/files/dnsmasq.conf ${D}${sysconfdir}/
    install -m 0755 ${WORKDIR}/git/openwrt/package/network/services/dnsmasq/files/dhcp.conf ${D}${sysconfdir}/config/dhcp
    install -m 0755 ${WORKDIR}/git/openwrt/package/network/services/dnsmasq/files/dnsmasq.init ${D}${sysconfdir}/init.d/dnsmasq

    ln -s ../init.d/dnsmasq ${D}${sysconfdir}/rc.d/S60dnsmasq
}
