# Provide uboot files for vf61 products
#
# Vf610 specific
do_configure_prepend_vf() {

   install -d ${S}/arch/arm/include/asm/arch-vf610
   install -d ${S}/board/toradex/mx4_vf

   install -m 0644 ${THISDIR}/files/board/toradex/mx4_vf/imximage.cfg ${S}/board/toradex/mx4_vf/imximage.cfg
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_vf/Kconfig ${S}/board/toradex/mx4_vf/Kconfig
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_vf/Makefile ${S}/board/toradex/mx4_vf/Makefile
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_vf/mx4_vf.c ${S}/board/toradex/mx4_vf/mx4_vf.c

   install -m 0644 ${THISDIR}/files/include/configs/mx4-vf-common.h ${S}/include/configs/mx4-vf-common.h

   # same as the mx4_base.inc but vf does not have machine overidde tegra so I place it here for now.
   install -d 0644 ${S}/board/toradex/mx4_common

   install -m 0644 ${THISDIR}/files/board/toradex/mx4_common/cmd_mx4_pic.c ${S}/board/toradex/mx4_common/cmd_mx4_pic.c
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_common/cmd_mx4_product.c ${S}/board/toradex/mx4_common/cmd_mx4_product.c
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_common/mx4_common.c ${S}/board/toradex/mx4_common/mx4_common.c
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_common/mx4_common.h ${S}/board/toradex/mx4_common/mx4_common.h

   # The dtb makefile need the c61 so for now it will be here. C61 is a prototype so we might remove it in the future.
   install -m 0644 ${THISDIR}/files/arch/arm/dts/vf610-mx4-v61.dts ${S}/arch/arm/dts/vf610-mx4-v61.dts
   install -m 0644 ${THISDIR}/files/arch/arm/dts/vf610-mx4-c61.dts ${S}/arch/arm/dts/vf610-mx4-c61.dts
}

# V61 specific files
do_configure_prepend_mx4-v61 () {

   install -d ${S}/arch/arm/dts

   install -m 0644 ${THISDIR}/files/include/configs/mx4_v61.h ${S}/include/configs/mx4_v61.h

}
# C61 specific files
do_configure_prepend_mx4-c61 () {

   install -d ${S}/arch/arm/dts

   install -m 0644 ${THISDIR}/files/include/configs/mx4_c61.h ${S}/include/configs/mx4_c61.h

}