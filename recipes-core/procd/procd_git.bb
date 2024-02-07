# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "procd is the new OpenWrt process management daemon written in C"
DESCRIPTION = "procd is both both VIRTUAL-RUNTIME-init_manager and \
              VIRTUAL_RUNTIME-dev_manager (like systemd/systemd-udev) \
              "
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SECTION = "base"
DEPENDS = "libubox ubus json-c"

SRC_URI = "git://git.openwrt.org/project/procd.git;protocol=https;branch=master \
           file://0200-fix-build.patch \
           file://00_preinit.conf \
           file://banner.failsafe \
           file://10_sysinfo \
           "

SRCREV:pn-procd = "7a0096853594874d4c60266ec338ac23728017df"

S = "${WORKDIR}/git"
PD = "${S}/openwrt/package/system/procd/files"
BF = "${S}/openwrt/package/base-files/files"

inherit cmake openwrt openwrt-services pkgconfig openwrt-base-files update-alternatives

SRCREV_openwrt = "${OPENWRT_SRCREV}"

TARGET_CFLAGS:append = " -Wno-error=array-bounds -Wno-error=unused-result"

do_install:append() {
    # Early init
    install -Dm 0755 ${BF}/etc/preinit ${D}${sysconfdir}/preinit
    install -Dm 0755 ${BF}/etc/diag.sh ${D}${sysconfdir}/diag.sh
    install -Dm 0644 ${BF}/lib/functions/preinit.sh ${D}${base_libdir}/functions/preinit.sh
    install -Dm 0644 ${BF}/rom/note ${D}/rom/note
    install -Dm 0544 ${WORKDIR}/banner.failsafe ${D}${sysconfdir}/banner.failsafe

    install -d ${D}${base_libdir}
    cp --preserve=mode,timestamps -R ${BF}/lib/preinit ${D}${base_libdir}
    install -Dm 0644 ${WORKDIR}/10_sysinfo ${D}${base_libdir}/preinit/10_sysinfo
    install -Dm 0644 ${WORKDIR}/00_preinit.conf ${D}${base_libdir}/preinit/00_preinit.conf

    # Early init + dev manager / coldplug
    install -Dm 0644 ${PD}/hotplug-preinit.json ${D}${sysconfdir}/hotplug-preinit.json

    # Init
    install -Dm 0644 ${PD}/procd.sh ${D}${base_libdir}/functions/procd.sh
    install -Dm 0755 ${PD}/reload_config ${D}${base_sbindir}/reload_config
    install -Dm 0644 ${BF}/lib/functions/service.sh ${D}${base_libdir}/functions/service.sh
    install -Dm 0755 ${BF}/etc/rc.common ${D}${sysconfdir}/rc.common
    install -Dm 0755 ${BF}/etc/rc.local ${D}${sysconfdir}/rc.local
    install -Dm 0755 ${BF}/etc/init.d/done ${D}${sysconfdir}/init.d/done
    install -Dm 0755 ${BF}/etc/init.d/sysctl ${D}${sysconfdir}/init.d/sysctl
    install -Dm 0755 ${BF}/etc/init.d/umount ${D}${sysconfdir}/init.d/umount
    install -Dm 0755 ${BF}/usr/libexec/login.sh ${D}/usr/libexec/login.sh
    install -dm 0755 ${D}/etc/rc.d
    # FIXME: Need to split openwrt base-files 'boot' script so that
    # things that need to be done by procd even when not on a full
    # openwrt image get done without errors
    # and things which are openwrt-image specific stay in base-files
    install -Dm 0755 ${BF}/etc/init.d/boot ${D}${sysconfdir}/init.d/boot

    # Dev manager / hotplug / coldplug
    install -Dm 0644 ${PD}/hotplug.json ${D}${sysconfdir}/hotplug.json
    install -d ${D}${sysconfdir}
    cp --preserve=mode,timestamps -R ${BF}/etc/rc.button ${D}${sysconfdir}
    install -Dm 0755 ${BF}/sbin/hotplug-call ${D}${base_sbindir}/hotplug-call
    cp -dR --preserve=mode,links ${BF}/etc/hotplug.d ${D}${sysconfdir}

    # Common default PATH
    sed -i "s#%PATH%#/usr/sbin:/usr/bin:/sbin:/bin#g" \
          ${D}${sysconfdir}/preinit \
          ${D}${base_libdir}/preinit/00_preinit.conf \
          ${D}${base_sbindir}/hotplug-call

    # Make sure things are where they are expected to be
    install -dm 0755 ${D}/sbin ${D}/lib
    ln -s /usr/sbin/procd ${D}/sbin/procd
    ln -s /usr/sbin/init ${D}/sbin/init
    ln -s /usr/sbin/askfirst ${D}/sbin/askfirst
    ln -s /usr/sbin/udevtrigger ${D}/sbin/udevtrigger
    mv ${D}${libdir}/libsetlbf.so ${D}${base_libdir}/libsetlbf.so
    rmdir ${D}/usr/lib
}

RDEPENDS:${PN} += "\
                  fstools \
                  base-files-scripts-openwrt \
                  ${PN}-inittab \
                  "

FILES:${PN} = "/"

ALTERNATIVE:${PN} = "init"

ALTERNATIVE_PRIORITY = "40"
ALTERNATIVE_TARGET[init] = "${base_sbindir}/init"

python () {
    if not bb.utils.contains('DISTRO_FEATURES', 'procd', True, False, d):
        raise bb.parse.SkipPackage("'procd' not in DISTRO_FEATURES")
}
