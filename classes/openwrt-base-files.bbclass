# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

# Use this git SRCREV for all recipes that pull files out of openwrt repository
# Equivalent to tag v17.01.4

OPENWRT_SRCREV = "dd3464023f1836ef4fa417509830aaf5618b9ae9"

LICENSE_append = "& GPL-2.0+"

OPENWRT_BASEPATH ?= "${S}/../git/openwrt"

LIC_FILES_CHKSUM_append = " file://${OPENWRT_BASEPATH}/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 "

SRC_URI_append = "\
	git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt \
	"

