# Provides common Tegra files on MX-4 products 
# In future posisible compile this files instead of just copy it to workdir.
# mx4-tegra3-base.inc (t30) or mx4-tegra2-base.inc(t20) or mx4-vf-base.inc(vf61) includes the makefile and additional files.

do_configure_prepend_tegra() {

   install -d 0644 ${S}/board/toradex/mx4_common

   install -m 0644 ${THISDIR}/files/board/toradex/mx4_common/cmd_mx4_pic.c ${S}/board/toradex/mx4_common/cmd_mx4_pic.c
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_common/cmd_mx4_product.c ${S}/board/toradex/mx4_common/cmd_mx4_product.c
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_common/mx4_common.c ${S}/board/toradex/mx4_common/mx4_common.c
   install -m 0644 ${THISDIR}/files/board/toradex/mx4_common/mx4_common.h ${S}/board/toradex/mx4_common/mx4_common.h
}