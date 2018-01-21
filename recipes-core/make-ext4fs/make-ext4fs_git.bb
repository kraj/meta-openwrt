# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Standalone fork of Android's make_ext4fs utility"
HOMEPAGE = "https://git.openwrt.org/?p=project/make_ext4fs.git;a=summary"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=bb2810bf31da2f6bb39e0bfa86091da3"
SECTION = "tools"
DEPENDS = "zlib"

SRC_URI = "git://git.openwrt.org/project/make_ext4fs.git \
          "
SRCREV = "bd53eaafbc2a89a57b8adda38f53098a431fa8f4"

S = "${WORKDIR}/git"
B = "${S}"

CFLAGS += "-I${S}/include -I${S}/libsparse/include"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
	install -Dm 0755 ${B}/make_ext4fs ${D}${bindir}/make_ext4fs
}
