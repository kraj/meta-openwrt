# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://fragment-lock.cfg \
    file://fragment-noifupdown.cfg \
    file://220-add_lock_util.patch \
    file://z300-fix_off_t_misdetection_triggered_without_LFS.patch \
"
do_install_append () {
    rm -f ${D}/usr/share/udhcpc/default.script
}
