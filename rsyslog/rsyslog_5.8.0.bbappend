FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

SRC_URI += "\
   file://rsyslog.conf \
   file://rsyslog.service \
   "

do_install_append() {
    install -d ${D}/${sysconfdir}/${PN}
    install -d ${D}${base_libdir}/systemd/system
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/

    install ${WORKDIR}/rsyslog.conf ${D}${sysconfdir}/rsyslog.conf

    install ${WORKDIR}/rsyslog.service ${D}${base_libdir}/systemd/system/rsyslog.service
}