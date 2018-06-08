# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

# Use this git SRCREV for all recipes that pull files out of openwrt repository
# Equivalent to tag v17.01.4

OPENWRT_SRCREV = "444add156f2a6d92fc15005c5ade2208a978966c"

LICENSE_append = "&GPL-2.0+"
LIC_FILES_CHKSUM_append = " file://${S}/../git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI_append = "\
	git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt;branch=lede-17.01 \
	"

