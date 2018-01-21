# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>

# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "OpenWrt Base Image"

LICENSE = "MIT"

require recipes-extended/images/openwrt-image-base.bb

CORE_IMAGE_EXTRA_INSTALL += "\
    packagegroup-openwrt-full \
    mountd \
    ugps \
    usbmode \
"
