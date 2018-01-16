# Note that VIRTUAL-RUNTIME_network_manager nor VIRTUAL-RUNTIME_syslog
# are essential for booting a standalone system so not included here.

inherit openwrt

RDEPENDS_${PN}_append = "\
    ${VIRTUAL-RUNTIME_kmod_manager} \
    "
