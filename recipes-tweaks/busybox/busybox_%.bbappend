# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG ??= ""
PACKAGECONFIG[oeoveropenwrt] = ""

SRC_URI += "\
    file://fragment-lock.cfg \
    ${@bb.utils.contains('PACKAGECONFIG', 'oeoveropenwrt', '', 'file://fragment-noifupdown.cfg', d)} \
    file://220-add_lock_util.patch \
"

do_install:append () {
    rm -f ${D}/usr/share/udhcpc/default.script
}
