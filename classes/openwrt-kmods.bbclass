#/ This file Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

do_rootfs[depends] += " virtual/kernel:do_shared_workdir"

ROOTFS_POSTUNINSTALL_COMMAND:append = ' openwrt_flatten_modules_hook; '

openwrt_flatten_modules_hook () {
    export KERNEL_VERSION="${@oe.utils.read_file('${STAGING_KERNEL_BUILDDIR}/kernel-abiversion')}"
    [ -n "${KERNEL_VERSION}" ]
    if [ -d "${IMAGE_ROOTFS}/lib/modules/${KERNEL_VERSION}" ]; then
        cd ${IMAGE_ROOTFS} && find lib/modules/${KERNEL_VERSION} -name '*.ko' -exec sh -c 'mod="{}"; ln -sf /$mod ${IMAGE_ROOTFS}/lib/modules/${KERNEL_VERSION}/$(basename "$mod")' \;
    fi
}
