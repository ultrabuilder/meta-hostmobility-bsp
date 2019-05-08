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

# U-Boot NAND binary includes 0x400 padding required for NAND boot
UBOOT_BINARY ?= "u-boot.bin"
UBOOT_MAKE_TARGET = "u-boot.bin"

IMAGE_FSTYPES = "tar.xz"


SERIAL_CONSOLE ?= "115200 ttyLP0"

