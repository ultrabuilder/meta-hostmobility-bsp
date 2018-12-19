# Provide uboot files for t30 products
#
S = "${WORKDIR}/git"
do_configure_prepend_tegra3() {

   install -d ${S}/board/toradex/mx4_t30


   install -m 0644 ${THISDIR}/files/board/toradex/mx4_t30/Kconfig ${S}/board/toradex/mx4_t30/Kconfig
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_t30/Makefile ${S}/board/toradex/mx4_t30/Makefile
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_t30/mx4_t30.c ${S}/board/toradex/mx4_t30/mx4_t30.c
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_t30/pinmux-config-mx4_t30.h ${S}/board/toradex/mx4_t30/pinmux-config-mx4_t30.h
   
   install -m 0644 ${THISDIR}/files/include/configs/mx4_t30.h ${S}/include/configs/mx4_t30.h
}