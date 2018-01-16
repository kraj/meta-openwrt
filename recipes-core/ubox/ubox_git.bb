DESCRIPTION = "OpenWrt system helper toolbox"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubox"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://kmodloader.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SRCREV = "16f7e16181e2f3e9cf3e2ce56a7e291844900d09"

DEPENDS = "ubus libubox uci"

SRC_URI = "\
    git://git.lede-project.org/project/ubox.git \
    file://log.init \
"

SRCREV = "16f7e16181e2f3e9cf3e2ce56a7e291844900d09"
S = "${WORKDIR}/git"

inherit cmake openwrt-services

do_install_append () {
        install -Dm 0755 ${WORKDIR}/log.init ${D}/etc/init.d/log
        ln -s /sbin/kmodloader ${D}/usr/sbin/rmmod
        ln -s /sbin/kmodloader ${D}/usr/sbin/insmod
        ln -s /sbin/kmodloader ${D}/usr/sbin/lsmod
        ln -s /sbin/kmodloader ${D}/usr/sbin/modinfo
        ln -s /sbin/kmodloader ${D}/usr/sbin/modprobe

        install -dm 0755 ${D}/sbin
        ln -s /usr/sbin/kmodloader ${D}/sbin/kmodloader
        ln -s /usr/sbin/logd ${D}/sbin/logd
        ln -s /usr/sbin/logread ${D}/sbin/logread
        ln -s /usr/sbin/validate_data ${D}/sbin/validate_data
        ln -s /usr/sbin/getrandom ${D}/sbin/getrandom
}

RDEPENDS_${PN} += "ubus libubox"

FILES_SOLIBSDEV = ""

FILES_${PN}  += "${libdir}/*.so"
