# Copyright (C) 2020 Host Mobility AB

SUMMARY = "Host Mobility BSP Linux kernel for MX-5 products"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

DEPENDS += "lzop-native bc-native"

LINUX_VERSION ?= "4.19.35"
LINUX_VERSION_EXTENSION_append = "-mobilityx"

SRC_URI = "\
    git://git@gitlab.com/hostmobility/linux-mx5;branch=${SRCBRANCH};protocol=ssh;nocheckout=1 \
    file://0001-Compiler-Attributes-add-support-for-__copy-gcc-9.patch \
    file://0002-include-linux-module.h-copy-__init-__exit-attrs-to-i.patch \
    file://0001-perf-Make-perf-able-to-build-with-latest-libbfd.patch \
"
SRCBRANCH = "imx_4.19.35_1.0.0_mx5_bringup"
SRCREV = "f407442a8eaf7617f85bd2de47b8da47fb6d7b1d"

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONFIG_MODE="--alldefconfig"

COMPATIBLE_MACHINE = "mx5-pt"
KBUILD_DEFCONFIG_mx5-pt ?= "mx5_imx6qp_defconfig"

do_configure_append() {
    # Disable the built-in driver, this means that kernel-module-imx-gpu-viv
    # will be used instead which is provided by meta-freescale and built as
    # an out of tree module
    #
    # This makes the GPU driver independent of the Linux kernel version
    sed -i -e "/CONFIG_MXC_GPU_VIV[ =]/d" '${B}/.config'
    echo "# CONFIG_MXC_GPU_VIV is not set" >> '${B}/.config'
}