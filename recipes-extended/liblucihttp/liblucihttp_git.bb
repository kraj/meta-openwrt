# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6b7565d075eb26cd08b6ac739db35e3"

SRC_URI = " \
    git://github.com/jow-/lucihttp;protocol=https \
    file://0001-CMakeLists.txt-remove-Werror-flag.patch \
"

DEPENDS = "lua5.1"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "a34a17d501c0e23f0a91dd9d3e87697347c861ba"

S = "${WORKDIR}/git"

# NOTE: unable to map the following pkg-config dependencies: (lua5.1 or lua-5.1 or lua)
#       (this is based on recipes that have previously been built and packaged)
inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "-DBUILD_LUA=off -DBUILD_TESTS=off"
