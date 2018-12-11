include u-boot-hostmobility.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

<<<<<<< Updated upstream
SRCREV = "552012e85e6b3eab7613be3a9426b2d5c89caad5"
SRCBRANCH = "2016.11-hm"
=======
SRCREV = "8668e85e64dbe5deb144e45f2eed5dfe0adfafc3"
SRCBRANCH = "2016.11-toradex"
>>>>>>> Stashed changes
PV = "v${SRCBRANCH}+git${SRCPV}"

DEFAULT_PREFERENCE = "1"



S = "${WORKDIR}/git"


##do_compile() {
##    ${CC} ${THISDIR}/files/board/toradex/${MACHINE}/${MACHINE}.c  
##}

do_install () {
    # install config fo target machine
    install -m 0644 ${THISDIR}/files/configs/${UBOOT_MACHINE} ${S}/configs/${UBOOT_MACHINE} || die "No default configuration for ${MACHINE} / ${UBOOT_MACHINE} available."

    if ["$MACHINE" = mx4-t30]; then
        install -d ${THISDIR}/files/board/toradex/mx4_t30
    elif ["$MACHINE" = mx4-t20];
        install -d ${THISDIR}/files/board/toradex/mx4_tegra2
    elif ["$MACHINE" = mx4-ct];
        install -d ${THISDIR}/files/board/toradex/mx4_tegra2
    elif ["$MACHINE" = mx4-vcc];
        install -d ${THISDIR}/files/board/toradex/mx4_tegra2
    elif ["$MACHINE" = mx4-gtt];
        install -d ${THISDIR}/files/board/toradex/mx4_tegra2
    elif ["$MACHINE" = mx4-mil];
        install -d ${THISDIR}/files/board/toradex/mx4_tegra2
    elif ["$MACHINE" = mx4-v61];
        install -d ${THISDIR}/files/board/toradex/mx4_vf
        install -m 0644 ${THISDIR}/files/arch/arm/cpu/armv7/vf610/Kconfig ${S}/arch/arm/cpu/armv7/vf610/Kconfig
    elif ["$MACHINE" = mx4-c61];
        install -d ${THISDIR}/files/board/toradex/mx4_vf
        install -m 0644 ${THISDIR}/files/arch/arm/cpu/armv7/vf610/Kconfig ${S}/arch/arm/cpu/armv7/vf610/Kconfig
    fi

    # for now just install pic and other common. (can be compiled here later)
    install -d 0644 ${THISDIR}/files/board/toradex/mx4_common

    #Overwrite default with this config(should be a patch?!), maybe cp to overide default file?
    install -m 0644 ${THISDIR}/files/drivers/spi/tegra20_slink.c ${S}/drivers/spi/tegra20_slink.c || die "No drivers/spi/tegra20_slink.c found"
}

# require u-boot-dtb-tegra.bin to be used
UBOOT_IMAGE_tegra = "u-boot-dtb-tegra-${MACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_BINARY_tegra = "u-boot-dtb-tegra.${UBOOT_SUFFIX}"
UBOOT_SYMLINK_tegra = "u-boot-dtb-tegra-${MACHINE}.${UBOOT_SUFFIX}"
