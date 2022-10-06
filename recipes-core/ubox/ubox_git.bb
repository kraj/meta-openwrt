DESCRIPTION = "OpenWrt system helper toolbox"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubox"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://kmodloader.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SRCREV = "205defb597295a4a8966db3e618cfe41a29bed99"

DEPENDS = "ubus libubox uci"

SRC_URI = "\
          git://git.openwrt.org/project/ubox.git;branch=master \
          file://log.init \
          "

S = "${WORKDIR}/git"

inherit cmake openwrt-services openwrt

do_install:append () {
        ${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'install -Dm 0755 ${WORKDIR}/log.init ${D}/etc/init.d/log', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/rmmod', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/insmod', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/lsmod', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/modinfo', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/modprobe', '', d)}
        install -dm 0755 ${D}/sbin
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /usr/sbin/kmodloader ${D}/sbin/kmodloader', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'ln -s /usr/sbin/logd ${D}/sbin/logd', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'ln -s /usr/sbin/logread ${D}/sbin/logread', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'ln -s /usr/sbin/validate_data ${D}/sbin/validate_data', '', d)}
}

RDEPENDS:${PN} += "\
                  ubus \
                  libubox \
                  "

FILES:${PN} += "\
               ${libdir} \
               ${base_sbindir} \
               "
