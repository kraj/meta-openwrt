# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM_remove = " file://openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "
LIC_FILES_CHKSUM_append = " file://../git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI += "file://99-dnsmasq.rules"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

inherit openwrt openwrt-services useradd openwrt-base-files

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
}

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "--system -d /var/lib/dnsmasq --no-create-home \
  --shell /bin/false --user-group dnsmasq"

RDEPENDS_dnsmasq += "jsonpath"

