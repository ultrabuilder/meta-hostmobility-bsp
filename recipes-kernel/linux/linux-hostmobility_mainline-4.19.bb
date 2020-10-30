SUMMARY = "Linux Kernel for Host Mobility products based on Toradex Tegra COMs"
SECTION = "kernel"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-hostmobility-mainline-4.19:"

inherit kernel siteinfo
include conf/tdx_version.conf

LINUX_VERSION ?= "4.19.66"

LOCALVERSION = "-${PR}"
PR = "${TDX_VER_ITEM}"

PV = "${LINUX_VERSION}"
S = "${WORKDIR}/linux-${PV}"

GENERIC_PATCHES = " \
"
MACHINE_PATCHES = " \
    file://0001-Add-device-tree-files-for-t30.patch \
    file://0002-Add-defconfig-file-for-t30.patch \
    file://0003-Add-mx4_pic-and-can-xcvr-to-spidev.patch \
    file://0004-Add-support-for-thermal-throttling.patch \
    file://0005-Add-support-for-flexray-device-driver.patch \
    file://0006-Add-flexray-driver-support.patch \
    file://0007-Add-pps-generator-gpio.patch \
    file://0008-mmc-read-mmc-alias-from-device-tree.patch \
    file://0009-Change-asix-driver-to-version-4.23.patch \
"


SRC_URI = " \
    https://cdn.kernel.org/pub/linux/kernel/v4.x/linux-${PV}.tar.xz \
    ${GENERIC_PATCHES} \
    ${MACHINE_PATCHES} \
"
SRC_URI[md5sum] = "025c07e37cb89afa2b26bb4850ce7fa2"
SRC_URI[sha256sum] = "c981abe15c4b9496cdadb04e4f4afb88b3a78f31ced8d07684ee9c58b3fadb64"

# For CI use one could use the following instead (plus patches still of course)
LINUX_VERSION_use-head-next ?= "4.19"
SRCREV_use-head-next = "${AUTOREV}"
PV_use-head-next = "${LINUX_VERSION}+git${SRCPV}"
S_use-head-next = "${WORKDIR}/git"
SRCBRANCH_use-head-next = "linux-4.19.y"
SRC_URI_use-head-next = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;protocol=git;branch=${SRCBRANCH} \
    ${GENERIC_PATCHES} \
    ${MACHINE_PATCHES} \
"


COMPATIBLE_MACHINE = "(mx4-t30)"
KERNEL_EXTRA_ARGS = " LOADADDR=0x80008000 "

# One possibiltiy for changes to the defconfig:
config_script () {
    echo "dummy" > /dev/null
    # Enable VLAN in kernel
    echo "CONFIG_VLAN_8021Q=y" >> ${B}/.config
}

do_configure_prepend () {
    cd ${S}
    export KBUILD_OUTPUT=${B}
    oe_runmake ${KERNEL_DEFCONFIG}

    #maybe change some configuration
    config_script

    #Add Toradex BSP Version as LOCALVERSION
    sed -i -e /CONFIG_LOCALVERSION/d ${B}/.config
    echo "CONFIG_LOCALVERSION=\"${LOCALVERSION}\"" >> ${B}/.config

    cd - > /dev/null
}

do_uboot_mkimage_prepend() {
    cd ${B}
}

