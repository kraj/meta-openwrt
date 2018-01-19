# Use this git SRCREV for all recipes that pull files out of openwrt repository
# Equivalent to tag v17.01.4
OPENWRT_SRCREV = "444add156f2a6d92fc15005c5ade2208a978966c"

LICENSE_append = "&GPL-2.0+"
LIC_FILES_CHKSUM_append = " file://openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI_append = "\
	git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;protocol=git;branch=lede-17.01 \
	"

