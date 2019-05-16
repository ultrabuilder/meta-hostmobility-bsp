# Provides common settings among MX-4 products that use Colibri VF61 COM

include conf/machine/include/imx-base.inc
include conf/machine/include/tune-cortexa7.inc

MACHINEOVERRIDES =. "mx7:mx7d"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-mx4-d71"
KERNEL_IMAGETYPE = "zImage"

# We store kernel on seperate MTD partition so no need to deploy to rootfs.
RDEPENDS_kernel-base = ""

PREFERRED_PROVIDER_u-boot ?= "u-boot-mx4-d71"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-mx4-d71"
PREFERRED_PROVIDER_u-boot-fw-utils ?= "u-boot-mx4-d71-fw-utils"

UBOOT_BINARY ?= "u-boot.bin"
UBOOT_MAKE_TARGET = "u-boot.bin"

IMAGE_FSTYPES = "wic.gz tar.xz"
# wic support
WKS_FILE = "sdimage-bootpart.wks"
WIC_CREATE_EXTRA_ARGS_append = " --no-fstab-update"

UBOOT_ENTRYPOINT = "0x82008000"
UBOOT_LOADADDRESS = "${UBOOT_ENTRYPOINT}"

SERIAL_CONSOLE ?= "115200 ttyLP0"

MACHINE_EXTRA_RDEPENDS_append = " \
    kernel-modules \
    kernel-module-pic \
    u-boot-hostmobility-fw-utils \
    usb-rndis-systemd \
    ${@bb.utils.contains('LICENSE_FLAGS_WHITELIST', 'closed', \
                          'packagegroup-hostmobility-commercial', '', d)} \
    usb-suspend-resume \
"