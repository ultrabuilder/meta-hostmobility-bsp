SUMMARY = "U-boot bootloader fw_printenv/setenv utils"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
SECTION = "bootloader"
PROVIDES = "u-boot-fw-utils"
RPROVIDES_${PN} = "u-boot-fw-utils"
DEPENDS = "mtd-utils"

include conf/tdx_version.conf
include mx4-base.inc
include mx4-tegra2-base.inc
include mx4-tegra3-base.inc
include mx4-vf-base.inc

DEFAULT_PREFERENCE = "1"

FILESPATHPKG =. "git:"

SRCREV = "07edca0bb81857a339f26f3465d5c5602705a94d"
SRCBRANCH = "2016.11-toradex"

SRC_URI = " \
    git://git.toradex.com/u-boot-toradex.git;protocol=https;branch=${SRCBRANCH} \
	file://default-gcc.patch \
    file://fw_env.config \
"
SRC_URI_append_vf = " \
    file://0001-Add-v61-and-c61-to-the-kconfig.patch \
    file://0002-Add-Hm-specific-device-tree-to-makefile.patch \
    file://0003-Add-HM-iomap-for-V61-UART-2.patch \
"

SRC_URI_append_tegra = " \
    file://0006-Add-HM-specific-t20-t30-spi-fix.patch \
"

SRC_URI_append_tegra2 = " \
    file://0002-Add-Hm-specific-device-tree-to-makefile.patch \
    file://0004-Add-HM-T20-config.patch \
"

SRC_URI_append_tegra3 = " \
    file://0005-Add-HM-T30-config.patch \
"

SRC_URI_append_tegra3 = " file://fw_unlock_mmc.sh"

PV = "v2016.11-hm+git${SRCPV}"
LOCALVERSION ?= "-${TDX_VER_ITEM}"

S = "${WORKDIR}/git"

INSANE_SKIP_${PN} = "already-stripped"
EXTRA_OEMAKE_class-target = 'CROSS_COMPILE=${TARGET_PREFIX} CC="${CC} ${CFLAGS} ${LDFLAGS}" HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}" V=1'
EXTRA_OEMAKE_class-cross = 'ARCH=${TARGET_ARCH} CC="${CC} ${CFLAGS} ${LDFLAGS}" V=1'

FILES_${PN} += " \
    /opt/hm/fw_env/fw_printenv \
    /opt/hm/fw_env/fw_setenv \
"

inherit pkgconfig uboot-config

do_configure_prepend () {

    # Install config fo target machine
    install -m 0644 ${THISDIR}/files/configs/${UBOOT_MACHINE} ${S}/configs/${UBOOT_MACHINE} || die "No default configuration for ${MACHINE} / ${UBOOT_MACHINE} available."
}

do_compile () {
    oe_runmake ${UBOOT_MACHINE}
    oe_runmake env
}

do_install () {
    install -d ${D}${base_sbindir} ${D}${sysconfdir}
    install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_printenv
    ln -s fw_printenv ${D}${base_sbindir}/fw_setenv
    
    install -d ${D}/opt/hm/fw_env
    install -m 755 ${S}/tools/env/fw_printenv ${D}/opt/hm/fw_env/fw_printenv
    ln -s fw_printenv ${D}/opt/hm/fw_env/fw_setenv

    install -m 644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/
}

do_install_append_tegra3() {
    install -d ${D}${sysconfdir}/profile.d/
    install -m 0644 ${WORKDIR}/fw_unlock_mmc.sh ${D}${sysconfdir}/profile.d/fw_unlock_mmc.sh
}

PACKAGE_ARCH = "${MACHINE_ARCH}"