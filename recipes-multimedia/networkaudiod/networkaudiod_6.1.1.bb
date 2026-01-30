SUMMARY = "NetworkAudio Daemon - High-Quality Network Audio Endpoint"
DESCRIPTION = "NetworkAudio Daemon is a network audio endpoint supporting high-resolution audio streaming with low-latency processing"
HOMEPAGE = "https://www.signalyst.com"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://copyright;md5=57be86e89d1805d6d08f227b0a11f960"

SRC_URI = "https://signalyst.com/bins/naa/linux/trixie/networkaudiod_6.1.1-69_arm64.deb \
           file://copyright \
           file://networkaudiod.service"
SRC_URI[sha256sum] = "b7142d91214ea0efb142a5ac5f306eea56bce081cd82321a7cfe8becb720ea1c"

S = "${UNPACKDIR}"

inherit systemd

# Yocto doesn't auto-unpack .deb files, so handle it manually
do_unpack:append() {
    import os

    # Find and extract deb file from DL_DIR
    deb_file = os.path.join(d.getVar('DL_DIR'), 'networkaudiod_6.1.1-69_arm64.deb')
    s_dir = d.getVar('S')

    if os.path.exists(deb_file):
        # Extract deb in source directory
        os.system('cd %s && ar -x %s && tar -xf data.tar.xz' % (s_dir, deb_file))
}

SYSTEMD_SERVICE:${PN} = "networkaudiod.service"
SYSTEMD_AUTO_ENABLE = "enable"

# NetworkAudio Daemon is a prebuilt binary for armv8
COMPATIBLE_MACHINE = "raspberrypi4-64|raspberrypi5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Skip QA checks for prebuilt binaries
INSANE_SKIP:${PN} = "already-stripped ldflags file-rdeps dev-so"

do_install() {
    # Copy extracted deb contents to rootfs
    cp -r ${S}/usr ${D}/ 2>/dev/null || true
    cp -r ${S}/etc ${D}/ 2>/dev/null || true

    # Install systemd service
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/networkaudiod.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "\
    ${sbindir}/networkaudiod \
    ${sbindir}/naa-start-uac-gadget.sh \
    ${bindir}/naa-control \
    ${sysconfdir}/default/networkaudiod \
    ${sysconfdir}/networkaudiod/* \
    ${datadir}/doc/networkaudiod/* \
    ${systemd_system_unitdir}/networkaudiod.service \
"

# Disable debug package generation
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
