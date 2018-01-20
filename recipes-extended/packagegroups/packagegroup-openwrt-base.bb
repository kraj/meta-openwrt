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
    packagegroup-openwrt-minimal \
    usign \
    "

RDEPENDS_${PN}-network = "\
    dnsmasq \
    \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'iwinfo', '',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'odhcpd', '', d)} \
    umdnsd \
    "

RDEPENDS_${PN}-luci = "\
    lua5.1 \
    luci \
    uhttpd \
    "
