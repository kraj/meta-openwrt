SUMMARY = "Normal Openwrt system requirements"
DESCRIPTION = "The set of packages required for a more traditional full-featured Openwrt system"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup openwrt openwrt-services

PACKAGES = "\
	packagegroup-openwrt-base \
	packagegroup-openwrt-base-base \
	packagegroup-openwrt-base-network \
	packagegroup-openwrt-base-luci \
	"

RDEPENDS_${PN} = "\
	packagegroup-openwrt-base-base \
	packagegroup-openwrt-base-network \
	packagegroup-openwrt-base-luci \
	"

RDEPENDS_${PN}-base = "\
    packagegroup-core-boot \
    rpcd \
    ubox \
    ubus \
    uci \
    usign \
    "

RDEPENDS_${PN}-network = "\
    dnsmasq \
    \
    firewall3 \
    iptables \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'iwinfo', '',d)} \
    ${VIRTUAL-RUNTIME_network_manager} \
    uclient \
    umdnsd \
    ustream-ssl \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'iw', '',d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'hostapd', '',d)} \
    "

RDEPENDS_${PN}-luci = "\
    lua5.1 \
    luci \
    uhttpd \
    "
