DESCRIPTION = "Control basic functions in socketcan from userspace"
HOMEPAGE = "http://www.pengutronix.de"
SECTION = "libs/network"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://src/libsocketcan.c;beginline=3;endline=17;md5=97e38adced4385d8fba1ae2437cedee1"

SRCREV = "4ea9ec7cf37a0c52f2c39a13887aaad11042ef5c"

SRC_URI = "git://git.pengutronix.de/git/tools/libsocketcan.git;protocol=git \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    sed -i -e s:tests/GNUmakefile::g -e s:trunk:0.0.10: configure.ac
}
