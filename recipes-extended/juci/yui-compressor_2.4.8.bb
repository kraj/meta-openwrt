SUMMARY = "A CSS and Javascript compressor for Node.js"
SECTION = "js/devel"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=25aff808793b8e978900033c658895b9"

SRC_URI = "https://github.com/yui/yuicompressor/archive/v${PV}.tar.gz"

SRC_URI[md5sum] = "a5a0b0d3c99e0a52e24a1da1560560eb"
SRC_URI[sha256sum] = "199ed2e4a05330b72cc1802347e2ae449112bb955894d44f596c1ef7319167bf"

S = "${WORKDIR}/yuicompressor-${PV}"

INSANE_SKIP_${PN} += "file-rdeps"

inherit npm-install-global allarch

BBCLASSEXTEND = "native nativesdk"
