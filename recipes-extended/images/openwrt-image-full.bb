SUMMARY = "OpenWrt Base Image"

LICENSE = "MIT"

require recipes-extended/images/openwrt-image-base.bb

CORE_IMAGE_EXTRA_INSTALL += "\
    packagegroup-openwrt-full \
    mountd \
    ugps \
    usbmode \
"
