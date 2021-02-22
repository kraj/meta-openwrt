# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://100-daemonize_fix.patch \
    file://110-wolfssl-compile-fix.patch \
    file://300-noscan.patch \
    file://320-optional_rfkill.patch \
    file://330-nl80211_fix_set_freq.patch \
    file://350-nl80211_del_beacon_bss.patch \
    file://360-ctrl_iface_reload.patch \
    file://381-hostapd_cli_UNKNOWN-COMMAND.patch \
    file://390-wpa_ie_cap_workaround.patch \
    file://430-hostapd_cli_ifdef.patch \
    file://432-missing-typedef.patch \
    file://450-scan_wait.patch \
    file://470-survey_data_fallback.patch \
    file://700-wifi-reload.patch \
"

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
