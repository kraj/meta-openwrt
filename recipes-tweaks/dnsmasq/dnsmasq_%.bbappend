inherit openwrt

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;protocol=git;branch=chaos_calmer"
SRC_URI += "file://99-dnsmasq.rules"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

RDEPENDS_dnsmasq += "jsonpath"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/config
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc.d
    install -d ${D}${sysconfdir}/udev
    install -d ${D}${sysconfdir}/udev/rules.d
    install -d ${D}${sbindir}

    install -m 0755 ${WORKDIR}/git/openwrt/package/network/services/dnsmasq/files/dnsmasq.conf ${D}${sysconfdir}/
    install -m 0755 ${WORKDIR}/git/openwrt/package/network/services/dnsmasq/files/dhcp.conf ${D}${sysconfdir}/config/dhcp
    install -m 0755 ${WORKDIR}/git/openwrt/package/network/services/dnsmasq/files/dnsmasq.init ${D}${sysconfdir}/init.d/dnsmasq
    install -m 0644 ${WORKDIR}/99-dnsmasq.rules ${D}${sysconfdir}/udev/rules.d/99-dnsmasq.rules

    # dnsmasq installs in /usr/bin, openwrt looks for it in /usr/sbin
    ln -s ${bindir}/dnsmasq ${D}${sbindir}/dnsmasq

    ln -s ../init.d/dnsmasq ${D}${sysconfdir}/rc.d/S60dnsmasq
}
