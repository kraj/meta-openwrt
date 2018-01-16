SUMMARY = "OpenWrt Base Image"

LICENSE = "MIT"

inherit core-image openwrt-services

CORE_IMAGE_BASE_INSTALL = '\
    packagegroup-core-boot-openwrt \
    kernel-modules \
    \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    '

IMAGE_INSTALL ?= "${CORE_IMAGE_BASE_INSTALL}"

CORE_IMAGE_EXTRA_INSTALL += "\
    packagegroup-core-full-openwrt \
    make-ext4fs \
    mountd \
    strace \
    ugps \
    usbmode \
"

IMAGE_FSTYPES += "ext4"

SRCDIR = "${THISDIR}/${PN}"
