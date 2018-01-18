
ROOTFS_POSTUNINSTALL_COMMAND_append = ' openwrt_flatten_modules_hook; '

openwrt_flatten_modules_hook () {
    export KERNEL_VERSION="${@base_read_file('${STAGING_KERNEL_BUILDDIR}/kernel-abiversion')}"
    if [ -d "${IMAGE_ROOTFS}/lib/modules/${KERNEL_VERSION}" ]; then
        cd ${IMAGE_ROOTFS} && find lib/modules/${KERNEL_VERSION} -name '*.ko' -exec sh -c 'mod="{}"; ln -sf /$mod ${IMAGE_ROOTFS}/lib/modules/${KERNEL_VERSION}/$(basename "$mod")' \;
    fi
}
