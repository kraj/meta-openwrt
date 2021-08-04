FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

SRC_URI:append = "\
    file://ipset.cfg \
    file://bridge.cfg \
"
