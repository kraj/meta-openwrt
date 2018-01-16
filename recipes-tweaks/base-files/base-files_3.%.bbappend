inherit openwrt openwrt-services

FILESEXTRAPATHS_prepend := "${THISDIR}/base-files-openwrt:"

DESCRIPTION = "Base files from openembedded and openwrt project"
HOMEPAGE = "http://wiki.openwrt.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI += "git://github.com/openwrt/openwrt.git;protocol=git;branch=lede-17.01 \
           file://0001-use-sh-not-ash.patch \
           "
SRCREV = "${OPENWRT_SRCREV}"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install_append () {
    rm -f ${D}${sysconfdir}/fstab
    rm -f ${D}${sysconfdir}/mtab
    cp -a ${S}/package/base-files/files/* ${D}
    chown -R root:root ${D}
    rm -f ${D}/etc/banner
    ln -sf /etc/issue ${D}/etc/banner
    mkdir -p ${D}/CONTROL
    mkdir -p ${D}/etc/crontabs
    mkdir -p ${D}/etc/rc.d
    mkdir -p ${D}/overlay
    mkdir -p ${D}/lib/firmware
    mkdir -p ${D}/sys
    mkdir -p ${D}/www
    mkdir -p ${D}${sysconfdir}/config
    ln -sf /proc/mounts ${D}/etc/mtab
    ln -sf /tmp/resolv.conf /tmp/fstab /tmp/TZ ${D}/etc/
    chmod 0600 ${D}/etc/shadow
    chmod 1777 ${D}/tmp

    sed -i "s#%PATH%#/usr/sbin:/usr/bin:/sbin:/bin#g" \
          ${D}/etc/profile

    rm -f ${D}/etc/preinit
    rm -rf ${D}/lib/preinit
    rm -f ${D}/sbin/hotplug-call
    rm -f ${D}/etc/hosts
    rm -f ${D}/etc/rpc
    rm -f ${D}/etc/services
    rm -f ${D}/etc/protocols
}

FILES_${PN} = "/"

CONFFILES_${PN} += " \
    ${sysconfdir}/fstab \
    ${@['', '${sysconfdir}/hostname'][(d.getVar('hostname', True) != '')]} \
    ${sysconfdir}/shells \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
