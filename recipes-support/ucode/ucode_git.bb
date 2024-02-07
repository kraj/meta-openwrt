LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b42eb47dc3802282b0d1be1bc8f5336c \
                    file://debian/copyright;md5=1675ccb2fa4c1fba3c778fd07a40b472"

SRC_URI = "git://github.com/jow-/ucode.git;protocol=https;branch=master"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "344fa9e69da43ecdc4d8f7768d85d42639352405"
PROVIDES = "ucode"

S = "${WORKDIR}/git"

DEPENDS = "json-c"

inherit cmake pkgconfig python3native

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = "\
	-DFS_SUPPORT=ON \
	-DMATH_SUPPORT=ON \
	-DNL80211_SUPPORT=OFF \
	-DRESOLV_SUPPORT=OFF \
	-DRTNL_SUPPORT=OFF \
	-DSTRUCT_SUPPORT=ON \
	-DUBUS_SUPPORT=OFF \
	-DUCI_SUPPORT=OFF \
	-DULOOP_SUPPORT=OFF \
	"

