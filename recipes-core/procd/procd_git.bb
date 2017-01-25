# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "procd is the new OpenWrt process management daemon written in C"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SECTION = "base"
DEPENDS = "libubox ubus json-c"

SRCREV = "f7069032290a9720142b2ee2c6315d7e1f1a1bd3"
SRC_URI = "git://git.openwrt.org/project/procd.git \
           file://procd.sh \
           file://reload_config \
           file://hotplug.json \
           file://hotplug-preinit.json \
"

inherit cmake openwrt pkgconfig

S = "${WORKDIR}/git"

do_install_append() {
    install -Dm 0755 ${WORKDIR}/procd.sh ${D}${base_libdir}/functions/procd.sh
    install -Dm 0755 ${WORKDIR}/reload_config ${D}${base_sbindir}/reload_config
    install -Dm 0755 ${WORKDIR}/hotplug.json ${D}${sysconfdir}/hotplug.json
    install -Dm 0755 ${WORKDIR}/hotplug-preinit.json ${D}${sysconfdir}/hotplug-preinit.json

    mkdir -p ${D}/sbin
    ln -s /usr/sbin/procd ${D}/sbin/procd
    ln -s /usr/sbin/init ${D}/sbin/init
    ln -s /usr/sbin/askfirst ${D}/sbin/askfirst
    ln -s /usr/sbin/udevtrigger ${D}/sbin/udevtrigger
}

FILES_${PN} += "${base_libdir}"
