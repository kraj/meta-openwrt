ROOTFS_POSTUNINSTALL_COMMAND_append += ' openwrt_enable_initscripts_hook; '

openwrt_enable_initscripts_hook () {
    for script in ${IMAGE_ROOTFS}/etc/init.d/*
    do
        grep '#!/bin/sh /etc/rc.common' $script > /dev/null || continue
        IPKG_INSTROOT=${IMAGE_ROOTFS} /bin/bash ${IMAGE_ROOTFS}/etc/rc.common $script enable || continue
    done
}
