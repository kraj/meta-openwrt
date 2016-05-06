DESCRIPTION = "OpenWrt system helper toolbox"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubox"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://kmodloader.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"

DEPENDS = "ubus libubox uci"

SRC_URI = "\
    git://nbd.name/luci2/ubox.git;protocol=git;branch=master \
    file://100-insmod-segfault.patch \
    file://log.init \
"

SRCREV = "31f0ff358b360ee461d845c1b3b5e5d38fa27925"
S = "${WORKDIR}/git"

inherit cmake

do_install_append () {
        install -Dm 0755 ${WORKDIR}/log.init ${D}/etc/init.d/log
        ln -s /sbin/kmodloader ${D}/usr/sbin/rmmod
        ln -s /sbin/kmodloader ${D}/usr/sbin/insmod
        ln -s /sbin/kmodloader ${D}/usr/sbin/lsmod
        ln -s /sbin/kmodloader ${D}/usr/sbin/modinfo
        ln -s /sbin/kmodloader ${D}/usr/sbin/modprobe
}

PACKAGES += "libvalidate"

#FILES_${PN} = "${bindir} ${sysconfdir} /sbin /usr/sbin"
#FILES_${PN}-dev = "/usr/include /usr/lib"
#FILES_libvalidate = "${base_libdir}"

#RDEPENDS_${PN} += "libvalidate ubus libubus libubox libuci"
