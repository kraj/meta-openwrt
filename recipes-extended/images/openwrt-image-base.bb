# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>

# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "OpenWrt Base Image"

LICENSE = "MIT"

require recipes-core/images/openwrt-image-minimal.bb

CORE_IMAGE_EXTRA_INSTALL += "\
    packagegroup-openwrt-base \
    make-ext4fs \
"
