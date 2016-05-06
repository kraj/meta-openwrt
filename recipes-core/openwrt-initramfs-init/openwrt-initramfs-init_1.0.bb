DESCRIPTION = "/init is a shell script that will find and mount the root \
partition and execute /bin/sh"

LICENSE = "CLOSED"

SRC_URI += "file://init"

do_install() {
    install -Dm 0755 ${WORKDIR}/init ${D}/init
}

FILES_${PN} = "/init"
