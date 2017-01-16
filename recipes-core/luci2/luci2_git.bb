DESCRIPTION = "Basic utility library"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../Makefile;beginline=1;endline=5;md5=3ce05a39dbd458b46a410c5a9f266107"

SRC_URI = "\
    git://git.openwrt.org/project/luci2/ui.git;protocol=git;branch=master \
    file://100-luci2-fix-compiler-warning.patch \
"

SRCREV = "e452ca693af5278ff2ddc69b6f8ed0f346c98fb1"
S = "${WORKDIR}/git/luci2/src/"
S_LUCI2 = "${WORKDIR}/git/luci2-p910nd/files/"

inherit cmake

DEPENDS = "rpcd uhttpd2 libubox ubus"

do_install_append () {
    install -Dm 0644 ${S_LUCI2}/www/luci2/template/services.p910nd.htm ${D}/www/luci2/template/services.p910nd.htm
    install -Dm 0644 ${S_LUCI2}/www/luci2/view/services.p910nd.js ${D}/www/luci2/view/services.p910nd.js
    install -Dm 0644 ${S_LUCI2}/usr/share/rpcd/acl.d/services.p910nd.json ${D}${datadir}/rpcd/acl.d/services.p910nd.json
    install -Dm 0644 ${S_LUCI2}/usr/share/rpcd/menu.d/services.p910nd.json ${D}${datadir}/rpcd/menu.d/services.p910nd.json
}

FILES_${PN} += "/usr /www"
