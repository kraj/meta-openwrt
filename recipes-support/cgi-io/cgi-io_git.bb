# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://git.openwrt.org/project/cgi-io.git;protocol=https;branch=master"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "901b0f0463c9d16a8cf5b9ed37118d8484bc9176"
PROVIDES = "cgi-io"
DEPENDS = "libubox ubus"
RDEPENDS:${PN} = "libubox ubus"
S = "${WORKDIR}/git"

inherit cmake python3native

do_install:append() {
	install -dm 0755 ${D}/www/cgi-bin
	ln -s /usr/sbin/cgi-io ${D}/www/cgi-bin/cgi-upload
	ln -s /usr/sbin/cgi-io ${D}/www/cgi-bin/cgi-download
	ln -s /usr/sbin/cgi-io ${D}/www/cgi-bin/cgi-backup
	ln -s /usr/sbin/cgi-io ${D}/www/cgi-bin/cgi-exec
}

FILES:${PN}  += "/www/cgi-bin/*"

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = ""

