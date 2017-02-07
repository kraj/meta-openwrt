SRC_URI += "git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;protocol=git;branch=chaos_calmer"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

FILES_${PN} += "${base_libdir}/netifd/hostapd.sh"

do_install_append() {
    install -d ${D}${base_libdir}/netifd

    install -m 0755 ${WORKDIR}/git/openwrt/package/network/services/hostapd/files/netifd.sh ${D}${base_libdir}/netifd/hostapd.sh
}
    
