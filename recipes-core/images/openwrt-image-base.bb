SUMMARY = "OpenWrt Base Image"

LICENSE = "MIT"

inherit core-image openwrt openwrt-kmods openwrt-services

CORE_IMAGE_BASE_INSTALL = '\
    packagegroup-core-boot \
    \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    '

IMAGE_INSTALL ?= "${CORE_IMAGE_BASE_INSTALL}"

CORE_IMAGE_EXTRA_INSTALL += "\
    packagegroup-openwrt-base \
    make-ext4fs \
    strace \
"

IMAGE_FSTYPES += "ext4"
