SUMMARY = "Minimal boot requirements"
DESCRIPTION = "The minimal set of packages required to boot the system"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup
#
# Set by the machine configuration with packages essential for device bootup
#
MACHINE_ESSENTIAL_EXTRA_RDEPENDS ?= ""
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS ?= ""

# For backwards compatibility after rename
RPROVIDES_${PN} = "task-core-boot"
RREPLACES_${PN} = "task-core-boot"
RCONFLICTS_${PN} = "task-core-boot"

# Distro can override the following VIRTUAL-RUNTIME providers:
VIRTUAL-RUNTIME_dev_manager ?= "procd"
VIRTUAL-RUNTIME_login_manager ?= "busybox"
VIRTUAL-RUNTIME_init_manager ?= "procd"
VIRTUAL-RUNTIME_keymaps ?= "keymaps"
VIRTUAL-RUNTIME_kmod_manager ?= "ubox"
VIRTUAL-RUNTIME_syslog ?= "ubox"

# Note that VIRTUAL-RUNTIME_network_manager nor VIRTUAL-RUNTIME_syslog
# are essential for booting a standalone system so not included here.

SYSVINIT_SCRIPTS = "${@bb.utils.contains('MACHINE_FEATURES', 'rtc', 'busybox-hwclock', '', d)} \
                    ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'kmod', 'modules-initscripts', '', d)} \
                    ${@bb.utils.contains('VIRTUAL-RUNTIME_network_manager', 'ifupdown', 'init-ifupdown', '', d)} \
                    ${@bb.utils.contains('VIRTUAL-RUNTIME_network_manager', 'busybox', 'init-ifupdown', '', d)} \
                   "

RDEPENDS_${PN} = "\
    base-files-openwrt \
    base-passwd \
    busybox \
    uci \
    ${@bb.utils.contains("MACHINE_FEATURES", "keyboard", "${VIRTUAL-RUNTIME_keymaps}", "", d)} \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_dev_manager} \
    ${VIRTUAL-RUNTIME_kmod_manager} \
    ${MACHINE_ESSENTIAL_EXTRA_RDEPENDS}"

RRECOMMENDS_${PN} = "\
    ${MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS}"
