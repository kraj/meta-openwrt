FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
    file://fragment-lock.cfg \
    file://220-add_lock_util.patch \
"

do_install_append () {
    rm -f ${D}/usr/share/udhcpc/default.script
}
