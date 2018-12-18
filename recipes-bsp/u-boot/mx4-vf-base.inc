# Provide uboot files for vf61 products
#
# Vf610 specific
do_configure_prepend_vf() {

   install -d ${S}/arch/arm/cpu/armv7/vf610/Kconfig
   install -d ${S}/arch/arm/dts
   install -d ${S}/arch/arm/include/asm/arch-vf610
   install -d ${S}/board/toradex/mx4_vf

   install -m 0644 ${THISDIR}/files/arch/arm/cpu/armv7/vf610/Kconfig ${S}/arch/arm/cpu/armv7/vf610/Kconfig

   install -m 0644 ${THISDIR}/files/arch/arm/dts/Makefile ${S}/arch/arm/dts/Makefile

   install -m 0644 ${THISDIR}/files/board/toradex/mx4_vf/imximage.cfg ${S}/board/toradex/mx4_vf/imximage.cfg
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_vf/Kconfig ${S}/board/toradex/mx4_vf/Kconfig
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_vf/Makefile ${S}/board/toradex/mx4_vf/Makefile
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_vf/mx4_vf.c ${S}/board/toradex/mx4_vf/mx4_vf.c

   install -m 0644 ${THISDIR}/files/include/configs/mx4-vf-common.h ${S}/include/configs/mx4-vf-common.h
}

# V61 specific files
do_configure_prepend_mx4-v61 () {

   install -d ${S}/arch/arm/dts

   install -m 0644 ${THISDIR}/files/arch/arm/dts/vf610-mx4-v61.dts ${S}/arch/arm/dts/vf610-mx4-v61.dts
   install -m 0644 ${THISDIR}/files/include/configs/mx4_t20.h ${S}/include/configs/mx4_v61.h

}
# C61 specific files
do_configure_prepend_mx4-c61 () {

   install -d ${S}/arch/arm/dts
   install -m 0644 ${THISDIR}/files/arch/arm/dts/vf610-mx4-c61.dts ${S}/arch/arm/dts/vf610-mx4-c61.dts
   install -m 0644 ${THISDIR}/files/include/configs/mx4_t20.h ${S}/include/configs/mx4_c61.h


}