FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

inherit systemd

SRC_URI += "\
   file://rsyslog.conf \
   file://rsyslog.service \
   "

do_install_append() {
    install -d ${D}/${sysconfdir}/${PN}
    install ${WORKDIR}/rsyslog.conf ${D}${sysconfdir}/rsyslog.conf
}

RPROVIDES_${PN} += "${PN}-systemd"
RREPLACES_${PN} += "${PN}-systemd"
RCONFLICTS_${PN} += "${PN}-systemd"
SYSTEMD_SERVICE_${PN} = "rsyslog.service"
SYSTEMD_AUTO_ENABLE = "disable"