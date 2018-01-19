# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "JUCI based OpenWRT GUI Image"
HOMEPAGE = ""
LICENSE = "MIT"

require recipes-core/images/openwrt-image-minimal.bb

IMAGE_FEATURES += "package-management"

IMAGE_INSTALL_append = "\
    juci \
    jucid \
    lighttpd \
    rpcd \
    uhttpd \
    "
