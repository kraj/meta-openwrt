SUMMARY = "OpenWrt Minimal Complete Image"

LICENSE = "MIT"

inherit core-image openwrt openwrt-kmods openwrt-services

CORE_IMAGE_BASE_INSTALL = '\
    packagegroup-core-boot \
    packagegroup-openwrt-minimal \
    \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
     '

IMAGE_INSTALL ?= "${CORE_IMAGE_BASE_INSTALL}"

IMAGE_FSTYPES += "ext4"
