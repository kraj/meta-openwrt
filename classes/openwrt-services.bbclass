# This file Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

ROOTFS_POSTUNINSTALL_COMMAND:append = ' openwrt_enable_initscripts_hook; '

openwrt_enable_initscripts_hook () {
    mkdir -p ${IMAGE_ROOTFS}/etc/rc.d
    for script in ${IMAGE_ROOTFS}/etc/init.d/*
    do
        grep '#!/bin/sh /etc/rc.common' $script > /dev/null || continue
        IPKG_INSTROOT=${IMAGE_ROOTFS} /bin/bash ${IMAGE_ROOTFS}/etc/rc.common $script enable || continue
    done
}
