SUMMARY = "Inittab configuration for procd"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://inittab \
	"

inherit update-alternatives

S = "${WORKDIR}"

INHIBIT_DEFAULT_DEPS = "1"

do_compile() {
	:
}

PROCD_ENABLED_TTYS ?= "${SYSVINIT_ENABLED_GETTYS}"

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}/inittab

    set -x
    tmp="${SERIAL_CONSOLES}"
    for i in $tmp
    do
	j=`echo ${i} | sed s/\;/\ /g`
	l=`echo ${i} | sed -e 's/^.*;//' -e 's/;.*//'`
	echo "$l::askfirst:/usr/libexec/login.sh" >> ${D}${sysconfdir}/inittab
    done

    if [ "${USE_VT}" = "1" ]; then
        for n in ${PROCD_ENABLED_TTYS}
        do
            echo "tty$n::askfirst:/usr/libexec/login.sh" >> ${D}${sysconfdir}/inittab
        done
        echo "" >> ${D}${sysconfdir}/inittab
    fi

    if [ -z "${SERIAL_CONSOLES}" ] && [ "${USE_VT}" != "1" ]; then
	echo "::askconsole:/usr/libexec.sh/login.sh" >> ${D}${sysconfdir}/inittab
        echo "" >${D}${sysconfdir}/inittab
    fi
}

pkg_postinst_${PN} () {
# run this on the target
if [ "x$D" = "x" ] && [ -e /proc/consoles ]; then
	tmp="${SERIAL_CONSOLES_CHECK}"
	for i in $tmp
	do
		j=`echo ${i} | sed -e s/^.*\;//g -e s/\:.*//g`
		k=`echo ${i} | sed s/^.*\://g`
		if [ -z "`grep ${j} /proc/consoles`" ]; then
			if [ -z "${k}" ] || [ -z "`grep ${k} /proc/consoles`" ] || [ ! -e /dev/${j} ]; then
				sed -i -e /^.*${j}\ /d -e /^.*${j}$/d /etc/inittab
			fi
		fi
	done
	kill -HUP 1
else
	if [ "${SERIAL_CONSOLES_CHECK}" = "" ]; then
		exit 0
	else
		exit 1
	fi
fi
}

ALTERNATIVE_PRIORITY = "200"
ALTERNATIVE_${PN} = "inittab"
ALTERNATIVE_TARGET[inittab] = "${sysconfdir}/inittab"

# USE_VT and SERIAL_CONSOLES are generally defined by the MACHINE .conf.
# Set PACKAGE_ARCH appropriately.
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "${sysconfdir}/inittab*"
CONFFILES_${PN} = "${sysconfdir}/inittab"

USE_VT ?= "1"
SYSVINIT_ENABLED_GETTYS ?= "1"
