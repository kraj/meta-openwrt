# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>

# Released under the MIT license (see COPYING.MIT for the terms)


# Note that VIRTUAL-RUNTIME_network_manager nor VIRTUAL-RUNTIME_syslog
# are essential for booting a standalone system so not included here.

inherit openwrt

RDEPENDS:${PN}:append = "\
    ${VIRTUAL-RUNTIME_kmod_manager} \
    "
