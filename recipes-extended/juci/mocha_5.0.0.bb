SUMMARY = "Mocha is a test framework for Node.js"
SECTION = "js/devel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=31f1cb7731bc1e684e18461ffcb1e916"

SRC_URI = "https://github.com/mochajs/mocha/archive/v${PV}.tar.gz"

SRC_URI[md5sum] = "827800b10c14eb6ebfb9bf0e73323339"
SRC_URI[sha256sum] = "f97b227f4d1240f790c2ee28d730012576d9e7f0e089f3efa8fad64fcd287fef"

S = "${WORKDIR}/mocha-${PV}"

INSANE_SKIP_${PN} += "file-rdeps"

inherit npm-install-global allarch

BBCLASSEXTEND = "native nativesdk"
