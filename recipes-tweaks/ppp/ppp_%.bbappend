FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://001-honor-ldflags.patch \
    file://010-use_target_for_configure.patch \
    file://100-debian_ip-ip_option.patch \
    file://101-debian_close_dev_ppp.patch \
    file://103-debian_fix_link_pidfile.patch \
    file://105-debian_demand.patch \
    file://106-debian_stripMSdomain.patch \
    file://107-debian_pppoatm_wildcard.patch \
    file://120-debian_ipv6_updown_option.patch \
    file://121-debian_adaptive_lcp_echo.patch \
    file://130-no_cdefs_h.patch \
    file://131-missing_prototype_macro.patch \
    file://132-fix_linux_includes.patch \
    file://133-fix_sha1_include.patch \
    file://140-pppoe_compile_fix.patch \
    file://201-mppe_mppc_1.1.patch \
    file://200-makefile.patch \
    file://204-radius_config.patch \
    file://205-no_exponential_timeout.patch \
    file://206-compensate_time_change.patch \
    file://207-lcp_mtu_max.patch \
    file://208-fix_status_code.patch \
    file://320-custom_iface_names.patch \
    file://321-multilink_support_custom_iface_names.patch \
    file://330-retain_foreign_default_routes.patch \
    file://340-populate_default_gateway.patch \
    file://400-simplify_kernel_checks.patch \
    file://401-no_record_file.patch \
    file://403-no_wtmp.patch \
    file://404-remove_obsolete_protocol_names.patch \
    file://405-no_multilink_option.patch \
    file://500-add-pptp-plugin.patch \
    file://510-pptp_compile_fix.patch \
    file://520-uniq.patch \
    file://530-pppoe_send_padt.patch \
    file://531-pppoe_no_disconnect_warning.patch \
    file://540-save-pppol2tp_fd_str.patch \
    file://550-fix-printer-args.patch \
"

do_install_append() {
    install -d ${D}/run/lock
}

FILES_${PN} += " \
    ${libdir}/pppd/ \
    /run/lock \
"
