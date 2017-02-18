inherit openwrt

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://300-noscan.patch"
SRC_URI += "git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;protocol=git;branch=chaos_calmer"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

FILES_${PN} += "\
    ${base_libdir}/netifd/hostapd.sh \
    ${base_libdir}/wifi/mac80211.sh \
    ${base_libdir}/netifd/wireless/mac80211.sh \
"

do_install_append() {
    install -d ${D}${base_libdir}/netifd
    install -d ${D}${base_libdir}/netifd/wireless
    install -d ${D}${base_libdir}/wifi

    install -m 0755 ${WORKDIR}/git/openwrt/package/network/services/hostapd/files/netifd.sh ${D}${base_libdir}/netifd/hostapd.sh

    install -m 0755 ${WORKDIR}/git/openwrt/package/kernel/mac80211/files/lib/wifi/mac80211.sh ${D}${base_libdir}/wifi/mac80211.sh
    install -m 0755 ${WORKDIR}/git/openwrt/package/kernel/mac80211/files/lib/netifd/wireless/mac80211.sh ${D}${base_libdir}/netifd/wireless/mac80211.sh
}
    
