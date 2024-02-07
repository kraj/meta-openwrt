# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

# Use this git SRCREV for all recipes that pull files out of openwrt repository
# Equivalent to tag v22.03

OPENWRT_SRCREV = "977f6f36a0121b213f9eee4a7deb7fdf073320e4"

LICENSE:append = "& GPL-2.0-or-later"

OPENWRT_BASEPATH ?= "${S}/../git/openwrt"

LIC_FILES_CHKSUM:append = " file://${OPENWRT_BASEPATH}/COPYING;md5=a8db84c7a073d2878849eee8eb0f5daa"

SRC_URI:append = "\
	git://github.com/openwrt/openwrt.git;branch=openwrt-22.03;protocol=https;name=openwrt;destsuffix=git/openwrt \
	"

SRCREV_FORMAT:prepend = "openwrt_"
