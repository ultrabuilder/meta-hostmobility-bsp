# Provide uboot files for t20 products
# For now just copy the files to work directory (source).

do_configure_prepend_tegra2() {
   install -d ${S}/arch/arm/dts
   install -d ${S}/board/toradex/mx4_tegra2


   install -m 0644 ${THISDIR}/files/arch/arm/dts/tegra20-mx4.dts ${S}/arch/arm/dts/tegra20-mx4.dts

   install -m 0644 ${THISDIR}/files/board/toradex/mx4_tegra2/Kconfig ${S}/board/toradex/mx4_tegra2/Kconfig
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_tegra2/Makefile ${S}/board/toradex/mx4_tegra2/Makefile
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_tegra2/mx4_tegra2.c ${S}/board/toradex/mx4_tegra2/mx4_tegra2.c

   install -m 0644 ${THISDIR}/files/include/configs/mx4-tegra20-common.h ${S}/include/configs/mx4-tegra20-common.h
}

# T20 specific files
do_configure_prepend_mx4-t20 () {

   install -m 0644 ${THISDIR}/files/include/configs/mx4_t20.h ${S}/include/configs/mx4_t20.h
}
# Ct (consat) specific files
do_configure_prepend_mx4-ct () {

   install -m 0644 ${THISDIR}/files/include/configs/mx4_ct.h ${S}/include/configs/mx4_ct.h
}
# Vcc specific files
do_configure_prepend_mx4-vcc () {

   install -m 0644 ${THISDIR}/files/include/configs/mx4_t20.h ${S}/include/configs/mx4_t20.h
}
# Gtt specific files
do_configure_prepend_mx4-gtt () {

    install -m 0644 ${THISDIR}/files/include/configs/mx4_t20.h ${S}/include/configs/mx4_t20.h

}
# Mil specific files
# which mil header file is used or is both used?
do_configure_prepend_mx4-mil () {

    install -m 0644 ${THISDIR}/files/include/configs/mx4_mil.h ${S}/include/configs/mx4_mil.h
    install -m 0644 ${THISDIR}/files/include/configs/mx4_mil_se.h ${S}/include/configs/mx4_mil_se.h

}