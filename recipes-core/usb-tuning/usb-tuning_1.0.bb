SUMMARY = "USB tuning parameters for audio optimization"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://usb-tuning.conf"

S = "${UNPACKDIR}"

inherit allarch

do_install() {
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 0644 ${UNPACKDIR}/usb-tuning.conf ${D}${sysconfdir}/modprobe.d/
}

FILES:${PN} = "${sysconfdir}/modprobe.d/usb-tuning.conf"
