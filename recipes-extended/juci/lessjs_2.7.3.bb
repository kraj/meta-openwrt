SUMMARY = "Leaner CSS"
DESCRIPTION = "less makes writing css easier by reducing duplication of source"
SECTION = "js/devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3bfd34238ccc26128aef96796a8bbf97"

SRC_URI = "https://github.com/less/less.js/archive/v${PV}.tar.gz"

SRC_URI[md5sum] = "68b2464f14d91888c7ec105a2e958f00"                                                          
SRC_URI[sha256sum] = "f92651be352ca8e87df17464e73b486dc39da289ef72f9f3502693e77d2f4d15" 

S = "${WORKDIR}/less.js-${PV}"

INSANE_SKIP_${PN} += "file-rdeps"

inherit npm-install-global allarch

BBCLASSEXTEND = "native nativesdk"
