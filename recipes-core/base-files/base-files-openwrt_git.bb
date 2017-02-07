DESCRIPTION = "Base files from openwrt project"
HOMEPAGE = "http://wiki.openwrt.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "git://github.com/openwrt/openwrt.git;protocol=git;branch=chaos_calmer \
           file://0001-use-sh-not-ash.patch \
           "
SRCREV = "${OPENWRT_SRCREV}"

inherit openwrt

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    cp -a ${S}/package/base-files/files/* ${D}
    chown -R root:root ${D}
    mkdir -p ${D}/CONTROL
    mkdir -p ${D}/dev
    mkdir -p ${D}/etc/crontabs
    mkdir -p ${D}/etc/rc.d
    mkdir -p ${D}/overlay
    mkdir -p ${D}/lib/firmware
    mkdir -p ${D}/mnt
    mkdir -p ${D}/proc
    mkdir -p ${D}/tmp
    mkdir -p ${D}/usr/lib
    mkdir -p ${D}/usr/bin
    mkdir -p ${D}/sys
    mkdir -p ${D}/www
    mkdir -p ${D}/root
    ln -sf /proc/mounts ${D}/etc/mtab
    mkdir -p ${D}/etc
    rm -rf ${D}/var
    ln -sf /tmp/resolv.conf /tmp/fstab /tmp/TZ ${D}/etc/
    chmod 0600 ${D}/etc/shadow
    chmod 1777 ${D}/tmp

    mkdir -p ${D}/var/run
    mkdir -p ${D}/etc/rc.d
    for script in ${D}/etc/init.d/*
    do
        grep '#!/bin/sh /etc/rc.common' $script > /dev/null || continue
        IPKG_INSTROOT=${D} /bin/bash ${D}/etc/rc.common $script enable || continue
    done
}

FILES_${PN} = "/"

CONFFILES_${PN} = " \
    ${sysconfdir}/fstab \
    ${@['', '${sysconfdir}/hostname'][(d.getVar('hostname', True) != '')]} \
    ${sysconfdir}/shells \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
