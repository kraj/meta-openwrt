# This file Copyright (C) 2015 Khem Raj <raj.kem@gmail.com> and
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

OECMAKE_C_FLAGS += "-DLUA_COMPAT_5_1"
EXTRA_OECMAKE += "-DLUAPATH=${libdir}/lua/5.1"

FILES:${PN}  += "${libdir}/* ${datadir}/lua/5.*/"
FILES:${PN}-dbg  += "${libdir}/lua/5.*/.debug"

DEPENDS += "lua5.1-native"
OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"
CFLAGS += "-I${STAGING_INCDIR}/lua5.1"

do_configure:prepend () {
    if [ -e "${S}/CMakeLists.txt" ] ; then
        sed -i -e \
	"s:ARCHIVE DESTINATION lib:ARCHIVE DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
	-e "s:LIBRARY DESTINATION lib:LIBRARY DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
	${S}/CMakeLists.txt
    fi
}
