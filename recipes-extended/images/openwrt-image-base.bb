SUMMARY = "OpenWrt Base Image"

LICENSE = "MIT"

require recipes-core/images/openwrt-image-minimal.bb

CORE_IMAGE_EXTRA_INSTALL += "\
    packagegroup-openwrt-base \
    make-ext4fs \
    strace \
"
