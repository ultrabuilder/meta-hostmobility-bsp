FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
require recipes-kernel/linux/linux-imx.inc
include conf/tdx_version.conf

SUMMARY = "Linux kernel for MX-4 products using Toradex Colibri imx7 COMs"

SRC_URI = "git://git.toradex.com/linux-toradex.git;protocol=git;branch=${SRCBRANCH} \
           file://defconfig"

# Load USB functions configurable through configfs (CONFIG_USB_CONFIGFS)
KERNEL_MODULE_AUTOLOAD += "${@bb.utils.contains('COMBINED_FEATURES', 'usbgadget', ' libcomposite', '',d)}"

PV_append = "+git${SRCPV}"

LOCALVERSION = "-4.9-1.0.x-imx"

SRCREV = "3bb6e3284a1bb88f142528537e6573f9d9f39aaa"
SRCBRANCH = "toradex_4.9-1.0.x-imx"
SRCREV_use-head-next = "${AUTOREV}"
SRCBRANCH_use-head-next = "toradex_4.9-1.0.x-imx-next"

DEPENDS += "lzop-native bc-native u-boot-mkimage-native"
COMPATIBLE_MACHINE = "(mx6|mx7|mx4-d71)"

do_deploy_append() {
    cd ${B}
    cat ${KERNEL_OUTPUT_DIR}/zImage ${KERNEL_OUTPUT_DIR}/dts/${KERNEL_DEVICETREE} > combined-image
    mkimage -A arm -C none -a ${UBOOT_ENTRYPOINT} -e ${UBOOT_ENTRYPOINT} -T kernel -d combined-image ${KERNEL_OUTPUT_DIR}/uImage
    cd -
    
    type=uImage
    base_name=${type}-${KERNEL_IMAGE_BASE_NAME}
    install -m 0644 ${KERNEL_OUTPUT_DIR}/${type} ${DEPLOYDIR}/${base_name}.bin

    symlink_name=uImage-${KERNEL_IMAGE_SYMLINK_NAME}
    ln -sf ${base_name}.bin ${DEPLOYDIR}/${symlink_name}.bin
    ln -sf ${base_name}.bin ${DEPLOYDIR}/${type}
}
