# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "procd is the new OpenWrt process management daemon written in C"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SECTION = "base"
DEPENDS = "libubox ubus json-c"

RDEPENDS_${PN} += " fstools"

# NB: Is both VIRTUAL-RUNTIME-init_manager and VIRTUAL_RUNTIME-dev_manager (like systemd/systemd-udev)

inherit cmake openwrt pkgconfig update-alternatives

RPROVIDES_${PN} = "procd"
RREPLACES_${PN} = "systemd sysvinit udev eudev"

SRCREV_pn-procd = "188353099cf6fc88f145cfcb84a4db3f6523528a"
SRCREV_openwrt = "${OPENWRT_SRCREV}"

SRC_URI = "git://git.openwrt.org/project/procd.git;branch=lede-17.01 \
	git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt/;branch=lede-17.01 \
	file://00_preinit.conf \
"

S = "${WORKDIR}/git"

do_install_append() {
    install -Dm 0644 ${S}/openwrt/package/system/procd/files/procd.sh ${D}${base_libdir}/functions/procd.sh
    install -Dm 0755 ${S}/openwrt/package/system/procd/files/reload_config ${D}${base_sbindir}/reload_config
    install -Dm 0644 ${S}/openwrt/package/system/procd/files/hotplug.json ${D}${sysconfdir}/hotplug.json
    install -Dm 0644 ${S}/openwrt/package/system/procd/files/hotplug-preinit.json ${D}${sysconfdir}/hotplug-preinit.json
    install -Dm 0755 ${S}/openwrt/package/base-files/files/sbin/hotplug-call ${D}${base_sbindir}/hotplug-call
    install -Dm 0755 ${S}/openwrt/package/base-files/files/etc/preinit ${D}${sysconfdir}/preinit

    install -dm 0755 ${D}${base_libdir}/preinit
    cp -R --preserve=mode  ${S}/openwrt/package/base-files/files/lib/preinit/* ${D}${base_libdir}/preinit/
    install -Dm 0644 ${WORKDIR}/00_preinit.conf ${D}${base_libdir}/preinit/00_preinit.conf

    sed -i "s#%PATH%#/usr/sbin:/usr/bin:/sbin:/bin#g" \
          ${D}${sysconfdir}/preinit \
          ${D}${base_libdir}/preinit/00_preinit.conf \
          ${D}${base_sbindir}/hotplug-call

    install -dm 0755 ${D}/sbin
    ln -s /usr/sbin/procd ${D}/sbin/procd
    ln -s /usr/sbin/init ${D}/sbin/init
    ln -s /usr/sbin/askfirst ${D}/sbin/askfirst
    ln -s /usr/sbin/udevtrigger ${D}/sbin/udevtrigger
}

FILES_${PN} += "${base_libdir}/*"

ALTERNATIVE_${PN} = "init"

ALTERNATIVE_PRIORITY = "40"

ALTERNATIVE_LINK_NAME[init] = "${base_sbindir}/init"

python () {
    if not bb.utils.contains('DISTRO_FEATURES', 'procd', True, False, d):
        raise bb.parse.SkipPackage("'procd' not in DISTRO_FEATURES")
}

