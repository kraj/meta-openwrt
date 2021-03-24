SUMMARY = "A cgi scripting program for embedded environments" 
DESCRIPTION = "Haserl is a small cgi wrapper that allows \"PHP\" style cgi \
programming,  but  uses  a  UNIX bash-like  shell  or Lua  as the programming \
language. It is very small, so it can be used in embedded environments, or \
where something like PHP is too big."
HOMEPAGE = "http://haserl.sourceforge.net/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = " \
    https://netix.dl.sourceforge.net/project/haserl/haserl-devel/haserl-${PV}.tar.gz \
"
SRC_URI[sha256sum] = "a1b633e80f3e2638e7f8f850786e95072cfd9877f88780092996fd6aaf7ae2da"

inherit autotools pkgconfig
