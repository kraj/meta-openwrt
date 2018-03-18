# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://300-noscan.patch"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

inherit openwrt openwrt-base-files

do_install_append() {
    install -d ${D}${base_libdir}/netifd
    install -d ${D}${base_libdir}/netifd/wireless
    install -d ${D}${base_libdir}/wifi

    install -m 0755 ${WORKDIR}/git/openwrt/package/kernel/mac80211/files/lib/wifi/mac80211.sh ${D}${base_libdir}/wifi/mac80211.sh
    install -m 0755 ${WORKDIR}/git/openwrt/package/kernel/mac80211/files/lib/netifd/wireless/mac80211.sh ${D}${base_libdir}/netifd/wireless/mac80211.sh
}

FILES_${PN} += "\
               ${base_libdir}/netifd/hostapd.sh \
               ${base_libdir}/wifi/mac80211.sh \
               ${base_libdir}/netifd/wireless/mac80211.sh \
"
