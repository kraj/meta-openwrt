SUMMARY = "OpenWrt Base Image"

LICENSE = "MIT"

inherit core-image image_types_uboot

CORE_IMAGE_BASE_INSTALL = '\
    packagegroup-core-boot-openwrt \
    kernel-modules \
    \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    '

IMAGE_INSTALL ?= "${CORE_IMAGE_BASE_INSTALL}"

CORE_IMAGE_EXTRA_INSTALL += "\
    dnsmasq \
    \
    firewall3 \
    fstools \
    iptables \
    iwinfo \
    jsonpath \
    libroxml \
    libubox \
    lua5.1 \
    lua-socket \
    luci \
    make-ext4fs \
    mdnsd \
    mountd \
    netifd \
    procd \
    relayd \
    rpcd \
    strace \
    ubox \
    ubus \
    uci \
    ugps \
    uhttpd2 \
    umbim \
    uqmi \
    usbmode \
    usign \
    ustream-ssl \
    openwrt-initramfs-init \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'iw', '',d)} \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'hostapd', '',d)} \
"

IMAGE_FSTYPES += "cpio.gz.u-boot"

SRCDIR = "${THISDIR}/${PN}"

rootfs_wifi_config() {
    install -d ${IMAGE_ROOTFS}/etc
    install -d ${IMAGE_ROOTFS}/etc/config

    install -m 0644 ${SRCDIR}/network_wireless.config ${IMAGE_ROOTFS}/etc/config/network
}

rootfs_wired_config() {
    install -d ${IMAGE_ROOTFS}/etc
    install -d ${IMAGE_ROOTFS}/etc/config

    install -m 0644 ${SRCDIR}/network_wired.config ${IMAGE_ROOTFS}/etc/config/network
}

ROOTFS_POSTPROCESS_COMMAND += " ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'rootfs_wifi_config; ', 'rootfs_wired_config; ',d)}"

