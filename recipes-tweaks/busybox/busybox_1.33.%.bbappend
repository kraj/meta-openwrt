# SPDX-FileCopyrightText: 2021 Bosch Sicherheitssysteme GmbH
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI += "file://z300-fix_off_t_misdetection_triggered_without_LFS.patch"
