include u-boot-hostmobility.inc

include mx4-base.inc
include mx4-tegra2-base.inc
include mx4-tegra3-base.inc
include mx4-vf-base.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

do_configure_prepend () {

    # Install config fo target machine
    install -m 0644 ${THISDIR}/files/configs/${UBOOT_MACHINE} ${S}/configs/${UBOOT_MACHINE} || die "No default configuration for ${MACHINE} / ${UBOOT_MACHINE} available."

}
