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
