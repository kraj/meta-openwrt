# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

OPENWRT_BASEPATH = "../git/openwrt"

SRC_URI += "file://99-dnsmasq.rules"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

inherit openwrt openwrt-services useradd openwrt-base-files gettext
DEPENDS = "ubus"

EXTRA_OEMAKE = "\
    'COPTS=${@bb.utils.contains('PACKAGECONFIG', 'dbus', '-DHAVE_DBUS', '', d)} \
           ${@bb.utils.contains('PACKAGECONFIG', 'idn', '-DHAVE_IDN', '', d)} \
           ${@bb.utils.contains('PACKAGECONFIG', 'conntrack', '-DHAVE_CONNTRACK', '', d)} \
           ${@bb.utils.contains('PACKAGECONFIG', 'lua', '-DHAVE_LUASCRIPT', '', d)} \
           -DHAVE_UBUS' \
    'CFLAGS=${CFLAGS}' \
    'LDFLAGS=${LDFLAGS}' \
"
do_install:append() {
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

USERADD_PARAM:${PN} = "--system -d /var/lib/dnsmasq --no-create-home \
  --shell /bin/false --user-group dnsmasq"

RDEPENDS:dnsmasq += "jsonpath"
