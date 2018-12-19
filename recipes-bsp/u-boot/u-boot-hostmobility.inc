# Copyright (C) 2017 Host Mobility AB
# Based on u-boot-toradex.inc Copyright (C) 2015 Toradex AG
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-bsp/u-boot/u-boot.inc

COMPATIBLE_MACHINE = "(mx4-t30|mx4-t20|mx4-ct|mx4-vcc|mx4-gtt|mx4-mil|mx4-t30-fr|mx4-c61|mx4-v61)"

inherit fsl-u-boot-localversion

SUMMARY = "U-Boot bootloader with support for MX-4 products from Host Mobility AB"
LICENSE = "GPLv2+"

DEPENDS += " dtc-native"

PROVIDES += "u-boot"

LOCALVERSION = " ${SRCBRANCH}"

PV = "v${SRCBRANCH}+git${SRCPV}"

SRCREV = "07edca0bb81857a339f26f3465d5c5602705a94d"
SRCBRANCH = "2016.11-toradex"

SRC_URI = "git://git.toradex.com/u-boot-toradex.git;protocol=https;branch=${SRCBRANCH}"

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

DEFAULT_PREFERENCE = "1"

S = "${WORKDIR}/git"

# FIXME: Allow linking of 'tools' binaries with native libraries
#        used for generating the boot logo and other tools used
#        during the build process.
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CPPFLAGS}" \
                 HOSTLDFLAGS="${BUILD_LDFLAGS}" \
                 HOSTSTRIP=true'

# require u-boot-dtb-tegra.bin to be used
UBOOT_IMAGE_tegra = "u-boot-dtb-tegra-${MACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_BINARY_tegra = "u-boot-dtb-tegra.${UBOOT_SUFFIX}"
UBOOT_SYMLINK_tegra = "u-boot-dtb-tegra-${MACHINE}.${UBOOT_SUFFIX}"