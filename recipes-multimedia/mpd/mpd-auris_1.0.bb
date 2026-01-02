SUMMARY = "MPD configuration for Auris with CPU isolation"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "file://mpd-auris.conf file://mpd-auris.service"

S = "${UNPACKDIR}"

inherit systemd allarch

DEPENDS = "mpd"
RDEPENDS:${PN} = "mpd"

SYSTEMD_SERVICE:${PN} = "mpd-auris.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${UNPACKDIR}/mpd-auris.conf ${D}${sysconfdir}/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/mpd-auris.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "${sysconfdir}/mpd-auris.conf ${systemd_system_unitdir}/mpd-auris.service"
