SUMMARY = "Extras Openwrt system requirements"
inherit packagegroup openwrt openwrt-services

PACKAGES = "\
	packagegroup-openwrt-full \
	packagegroup-openwrt-full-base \
	packagegroup-openwrt-full-network \
	"

RDEPENDS_${PN} = "\
	packagegroup-openwrt-base \
	packagegroup-openwrt-full-network \
	"

RDEPENDS_${PN}-network = "\
    relayd \
    tcpdump \
    umbim \
    umdnsd \
    uqmi \
    "
