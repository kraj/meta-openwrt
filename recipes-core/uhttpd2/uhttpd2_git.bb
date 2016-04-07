DESCRIPTION = "uHTTPd - tiny, single threaded HTTP server"
HOMEPAGE = "http://wiki.openwrt.org/doc/howto/http.uhttpd"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=ba30601dd30339f7ff3d0ad681d45679"

SRC_URI = "git://nbd.name/uhttpd2.git;protocol=git;branch=master"
SRC_URI += "file://ubus.default"
SRC_URI += "file://uhttpd.config"
SRC_URI += "file://uhttpd.init"
SRC_URI += "file://luajit.patch"
SRC_URI += "file://fix-bsd.patch"
		   
SRCREV = "b9178b9357798ae23a5724333cc6572d14f23958"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "ubus luajit libubox ustream-ssl"

#FIXME: put plugins to the correct place
FILES_${PN} += "/usr/lib/*.so"

do_install_append () {
	mkdir -p ${D}/${sysconfdir}/config ${D}/${sysconfdir}/init.d ${D}/usr/lib ${D}/etc/uci-defaults
	install ${WORKDIR}/uhttpd.config ${D}/${sysconfdir}/config/uhttpd
	install ${WORKDIR}/uhttpd.init ${D}/${sysconfdir}/init.d/uhttpd
	install ${WORKDIR}/ubus.default ${D}/etc/uci-defaults/00_uhttpd_ubus
	mv ${D}/usr/bin ${D}/usr/sbin
        install ${B}/uhttpd_lua.so ${D}/usr/lib/
        install ${B}/uhttpd_ubus.so ${D}/usr/lib/
}


