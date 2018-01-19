inherit openwrt openwrt-base-files

DESCRIPTION = "Base files from openembedded and openwrt project"
HOMEPAGE = "http://wiki.openwrt.org/"

LIC_FILES_CHKSUM_remove = " file://openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "
LIC_FILES_CHKSUM_append = " file://git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-openwrt:"

SRC_URI += "\
        file://0001-use-sh-not-ash.patch \
	"
SRCREV = "${OPENWRT_SRCREV}"

S = "${WORKDIR}"
SG = "${WORKDIR}/git/openwrt"
STMP = "${WORKDIR}/stmp"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

PACKAGECONFIG_append = "openwrt"

do_install_append () { 
    if [ "${@bb.utils.contains('PACKAGECONFIG', 'openwrt', 'true', 'false', d)}" = "true" ]; then
        # We need to munge openwrt base-files before copying
        # Some file come from regular OE base-files and others
        # belong in other recipes, or are not applicable
        rm -rf ${STMP}
        mkdir -p ${STMP}
        cp -dR --preserve=mode,links ${SG}/package/base-files/files/* ${STMP}/

        # procd - earlyinit
        rm -f ${STMP}/etc/inittab
        rm -f ${STMP}/etc/preinit
        rm -f ${STMP}/etc/diag.sh
        rm -f ${STMP}/lib/functions/preinit.sh
        rm -rf ${STMP}/lib/preinit
        rm -f ${STMP}/rom/note
        rm -f ${STMP}/etc/banner.failsafe
        # We want these to fail if Openwrt adds more to these dirs, so no rm -rf
        rmdir ${STMP}/rom

        # procd - init
        rm -f ${STMP}/lib/functions/service.sh
        rm -f ${STMP}/etc/rc.common
        rm -f ${STMP}/etc/rc.local
        rm -f ${STMP}/usr/libexec/login.sh
        rm -f ${STMP}/etc/init.d/done
        rm -f ${STMP}/etc/init.d/sysctl
        rm -f ${STMP}/etc/init.d/umount
        rm -f ${STMP}/etc/init.d/boot
        # We want these to fail if Openwrt adds more to these dirs, so no rm -rf
        rmdir ${STMP}/usr/libexec

        # procd - devmanager
        rm -rf ${STMP}/etc/rc.button
        rm -rf ${STMP}/etc/hotplug.d
        rm -f ${STMP}/sbin/hotplug-call

        # From OE base-files or netbase
        rm -f ${STMP}/etc/hosts
        rm -f ${STMP}/etc/rpc
        rm -f ${STMP}/etc/services
        rm -f ${STMP}/etc/protocols
        rm -f ${STMP}/etc/banner
        ln -sf /etc/issue ${STMP}/etc/banner

        # For netifd package
        rm -f ${STMP}/lib/functions/network.sh
        rm -f ${STMP}/sbin/wifi
        rm -f ${STMP}/etc/uci-defaults/12_network-generate-ula

        # Not applicable to OE flavour
        rm -f ${STMP}/etc/uci-defaults/10_migrate-shadow
        rm -f ${STMP}/etc/uci-defaults/11_migrate-sysctl

        # These depend on mechanisms not in OE build process
        rm -f ${STMP}/etc/openwrt_version
        rm -f ${STMP}/etc/openwrt_release
        rm -f ${STMP}/etc/uci-defaults/13_fix_group_user
        # We want this to fail if Openwrt adds more to this dir, so no rm -rf
        rmdir ${STMP}/etc/uci-defaults

        # In base-files-scripts-openwrt
        rm -f ${STMP}/lib/functions.sh
        rm -f ${STMP}/lib/functions/uci-defaults.sh
        rm -f ${STMP}/lib/functions/system.sh
        rm -f ${STMP}/bin/ipcalc.sh

        # In base-files-scripts-sysupgrade
        rm -f ${STMP}/etc/sysupgrade.conf
        rm -f ${STMP}/sbin/sysupgrade
        rm -rf ${STMP}/lib/upgrade
        rm -f ${STMP}/sbin/firstboot

        # Some files in standard base-files don't apply to openwrt flavour
        # These two are about avoiding flash writes
        rm -f ${D}${sysconfdir}/fstab
        rm -f ${D}${sysconfdir}/mtab

        # Copy what is applicable to rootfs
        cp -dR --preserve=mode,links ${STMP}/* ${D}
        rm -rf ${STMP}

        # FIXME: Should be OE's busybox crontabs dir
        mkdir -p ${D}${sysconfdir}/crontabs

        # FIXME: Should this change for OE?
        mkdir -p ${D}/overlay

        # Avoid flash writes
        ln -sf /tmp/resolv.conf /tmp/fstab /tmp/TZ ${D}/etc/
        ln -sf /proc/mounts ${D}/etc/mtab

        chmod 0600 ${D}/etc/shadow
        chmod 1777 ${D}/tmp

        sed -i "s#%PATH%#/usr/sbin:/usr/bin:/sbin:/bin#g" \
              ${D}/etc/profile

    fi
}

FILES_${PN} = "/"

RDEPENDS_${PN} += "\
	${@bb.utils.contains('PACKAGECONFIG', 'openwrt', '${PN}-scripts-openwrt', '', d)} \
	"

RSUGGESTS_${PN} += "\
	${@bb.utils.contains('PACKAGECONFIG', 'openwrt', '${PN}-scripts-sysupgrade procd ubox', '', d)} \
	"

CONFFILES_${PN} += "\
    ${sysconfdir}/fstab \
    ${@['', '${sysconfdir}/hostname'][(d.getVar('hostname', True) != '')]} \
    ${sysconfdir}/shells \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"
