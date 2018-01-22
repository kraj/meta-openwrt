# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://udev.procd"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d

    install -m 0755 ${WORKDIR}/udev.procd ${D}${sysconfdir}/init.d/udev

    mkdir -p ${D}${sysconfdir}/rc.d
    ln -s ../init.d/udev ${D}${sysconfdir}/rc.d/S05udev
    ln -s ../init.d/udev ${D}${sysconfdir}/rc.d/K95udev
}
