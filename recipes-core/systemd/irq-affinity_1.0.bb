SUMMARY = "Configure IRQ affinity to exclude isolated CPU core 3"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://irq-affinity.sh \
           file://irq-affinity.service"

S = "${UNPACKDIR}"

inherit allarch systemd

SYSTEMD_SERVICE:${PN} = "irq-affinity.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/irq-affinity.sh ${D}${bindir}/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/irq-affinity.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "${bindir}/irq-affinity.sh ${systemd_system_unitdir}/irq-affinity.service"
