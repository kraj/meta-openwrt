SUMMARY = "OpenWrt Base Image"

LICENSE = "MIT"

inherit core-image image_types_uboot

CORE_IMAGE_BASE_INSTALL = '\
    packagegroup-core-boot-openwrt \
    \
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
"

IMAGE_FSTYPES += "cpio.gz.u-boot"
