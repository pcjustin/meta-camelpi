SUMMARY = "Audio hardware detection for Auris"
DESCRIPTION = "Detects USB DAC or HiFiBerry and logs audio hardware configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://audio-detect.sh \
           file://audio-detect.service"

S = "${UNPACKDIR}"

inherit allarch systemd

RDEPENDS:${PN} = "alsa-utils"

SYSTEMD_SERVICE:${PN} = "audio-detect.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/audio-detect.sh ${D}${bindir}/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/audio-detect.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "${bindir}/audio-detect.sh ${systemd_system_unitdir}/audio-detect.service"
