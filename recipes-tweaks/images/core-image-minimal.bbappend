SUMMARY = "A small image just capable of allowing a device to boot."

inherit openwrt-services

IMAGE_INSTALL_remove = " packagegroup-core-boot "
IMAGE_INSTALL_prepend = " packagegroup-core-boot-openwrt "

