SUMMARY = "U-boot bootloader fw_printenv/setenv utils"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=c7383a594871c03da76b3707929d2919"
SECTION = "bootloader"
PROVIDES = "u-boot-fw-utils"
RPROVIDES_${PN} = "u-boot-fw-utils"
DEPENDS = "mtd-utils"

FILESPATHPKG =. "git:"

SRCREV = "808d51634befdf1ecf76ab66003ac225e7eeef37"
SRCBRANCH = "2016.11-hm"

SRC_URI = " \
    git://github.com/hostmobility/u-boot-toradex.git;protocol=https;branch=${SRCBRANCH} \
    file://fw_env.config \
"

SRC_URI_append_tegra3 = " file://fw_unlock_mmc.sh"

PV = "v2015.04-hm+git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = 'CC="${CC}" STRIP="${STRIP}"'

INSANE_SKIP_${PN} = "already-stripped ldflags"

inherit pkgconfig uboot-config

do_compile () {
    oe_runmake ${UBOOT_MACHINE}
    oe_runmake env
}

do_install () {
    install -d ${D}${base_sbindir} ${D}${sysconfdir}
    install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_printenv
    ln -s fw_printenv ${D}${base_sbindir}/fw_setenv
    install -m 644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
