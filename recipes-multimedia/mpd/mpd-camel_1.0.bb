SUMMARY = "MPD configuration for Camel Audio with CPU isolation"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "file://mpd-camel.conf file://mpd-camel.service"

S = "${UNPACKDIR}"

inherit systemd allarch

DEPENDS = "mpd"
RDEPENDS:${PN} = "mpd"

SYSTEMD_SERVICE:${PN} = "mpd-camel.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${UNPACKDIR}/mpd-camel.conf ${D}${sysconfdir}/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/mpd-camel.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "${sysconfdir}/mpd-camel.conf ${systemd_system_unitdir}/mpd-camel.service"
