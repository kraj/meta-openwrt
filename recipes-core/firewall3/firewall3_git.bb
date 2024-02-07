# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "OpenWrt firewall configuration utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/firewall3.git;a=summary"
LICENSE = "0BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2a8ffaa9ef41014f236ab859378e8900"
SECTION = "base"
DEPENDS = "libubox uci ubus iptables"

SRC_URI = "git://git.openwrt.org/project/firewall3.git;protocol=https;branch=master \
          "

SRCREV = "12f6f143106257e0921c6ebbca2fe329cbeb3de6"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt openwrt-services openwrt-base-files

SRCREV_openwrt = "${OPENWRT_SRCREV}"

EXTRA_OECMAKE = "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '-DDISABLE_IPV6=OFF', '-DDISABLE_IPV6=ON', d)}"

CFLAGS += "-Wno-error=format-overflow"
do_install:append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/config
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/hotplug.d/iface
    install -d ${D}${base_sbindir}

    install -m 0755 ${WORKDIR}/git/openwrt/package/network/config/firewall/files/firewall.init ${D}${sysconfdir}/init.d/firewall
    install -m 0644 ${WORKDIR}/git/openwrt/package/network/config/firewall/files/firewall.hotplug ${D}${sysconfdir}/hotplug.d/iface/20-firewall
    install -m 0644 ${WORKDIR}/git/openwrt/package/network/config/firewall/files/firewall.config ${D}${sysconfdir}/config/firewall
    install -m 0644 ${WORKDIR}/git/openwrt/package/network/config/firewall/files/firewall.user ${D}${sysconfdir}/firewall.user

    ln -s ${sbindir}/firewall3 ${D}${base_sbindir}/fw3

    # Be prepared for both procd and sysvinit/systemd style module loading
    install -dm 0755 ${D}${sysconfdir}/modules.d ${D}${sysconfdir}/modules-load.d
    if [ "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'true', 'false', d)}" = "true" ]; then

        # Can't indent the here-document because leading spaces confuse
        # kmodloader
        cat >${D}${sysconfdir}/modules.d/40-ip6tables <<EOF
ip6_tables
ip6table_filter
ip6table_mangle
ipt_REJECT
nf_nat_masquerade_ipv6
nf_defrag_ipv6
nf_conntrack_ipv6
EOF

       install -Dm 0644 ${D}${sysconfdir}/modules.d/40-ip6tables ${D}${sysconfdir}/modules-load.d/ip6tables.conf
    fi

    # Can't indent the here-document because leading spaces confuse
    # kmodloader
    cat >${D}${sysconfdir}/modules.d/iptables-fw3 <<EOF
ip_tables
xt_tcpudp
iptable_filter
iptable_mangle
iptable_nat
xt_limit
xt_mac
xt_multiport
xt_comment
xt_LOG
nf_log_common
nf_log_ipv4
xt_tcpmss
ipt_reject
xt_time
xt_mark
xt_nat
xt_NETMAP
nf_nat_ipv4
iptable_nat
ipt_MASQUERADE
xt_REDIRECT
xt_state
xt_CT
xt_conntrack
nf_defrag_ipv4
nf_conntrack_ipv4
nf_conntrack_netlink
nf_nat_masquerade_ipv4
nf_conntrack
EOF

    install -Dm 0644 ${D}${sysconfdir}/modules.d/iptables-fw3 ${D}${sysconfdir}/modules-load.d/iptables-fw3.conf
}

FILES:${PN} += "${libdir}/*"

RDEPENDS:${PN} = "ipset xtables-addons"

RRECOMMENDS:${PN} = "\
                    kernel-module-ip-tables \
                    kernel-module-xt-tcpudp \
                    kernel-module-iptable-filter \
                    kernel-module-iptable-mangle \
                    kernel-module-iptable-nat \
                    kernel-module-xt-limit \
                    kernel-module-xt-mac \
                    kernel-module-xt-multiport \
                    kernel-module-xt-comment \
                    kernel-module-xt-log \
                    kernel-module-nf-log-common \
                    kernel-module-nf-log-ipv4 \
                    kernel-module-xt-tcpmss \
                    kernel-module-ipt-reject \
                    kernel-module-xt-time \
                    kernel-module-xt-mark \
                    kernel-module-xt-netmap \
                    kernel-module-xt-nat \
                    kernel-module-nf-nat \
                    kernel-module-nf-nat-ipv4 \
                    kernel-module-nf-nat-masquerade-ipv4 \
                    kernel-module-ipt-masquerade \
                    kernel-module-xt-redirect \
                    kernel-module-nf-nat-redirect \
                    kernel-module-iptable-nat \
                    kernel-module-xt-state \
                    kernel-module-xt-ct \
                    kernel-module-nf-defrag-ipv4 \
                    kernel-module-nf-conntrack-ipv4 \
                    kernel-module-nf-conntrack-netlink \
                    kernel-module-xt-conntrack \
                    kernel-module-nf-conntrack \
                    "

RRECOMMENDS:${PN} += "\
                     ${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '\
                     kernel-module-ip6table-filter \
                     kernel-module-ip6table-mangle \
                     kernel-module-nf-log-ipv6 \
                     kernel-module-ip6t-reject \
                     kernel-module-nf-nat-masquerade-ipv6 \
                     kernel-module-nf-defrag-ipv6 \
                     kernel-module-nf-conntrack-ipv6 \
                     ', '' , d)} \
                     "

TOOLCHAIN = "gcc"
