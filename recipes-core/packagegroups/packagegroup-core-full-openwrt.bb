SUMMARY = "Normal Openwrt system requirements"
DESCRIPTION = "The set of packages required for a more traditional full-featured Openwrt system"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

VIRTUAL-RUNTIME_network_manager ?= "netifd"

PACKAGES = "\
	packagegroup-core-full-openwrt \
	packagegroup-core-full-openwrt-base \
	packagegroup-core-full-openwrt-network \
	packagegroup-core-full-openwrt-luci \
	packagegroup-core-full-openwrt-sys-services \
	"

RDEPENDS_${PN} = "\
	packagegroup-core-full-openwrt-base \
	packagegroup-core-full-openwrt-network \
	packagegroup-core-full-openwrt-luci \
	packagegroup-core-full-openwrt-sys-services \
	"

RDEPENDS_${PN}-base = "\
    packagegroup-core-boot-openwrt \
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
    relayd \
    tcpdump \
    uclient \
    umbim \
    umdnsd \
    uqmi \
    ustream-ssl \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'iw', '',d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'hostapd', '',d)} \
    "

RDEPENDS_${PN}-luci = "\
    lua5.1 \
    lua-socket \
    luci \
    uhttpd \
    "

RDEPENDS_${PN}-sys-services = "\
    ubox \
    "
