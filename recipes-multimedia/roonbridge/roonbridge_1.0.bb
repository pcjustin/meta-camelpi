SUMMARY = "RoonBridge - Network audio player endpoint"
DESCRIPTION = "RoonBridge turns your device into a Roon audio endpoint"
HOMEPAGE = "https://roonlabs.com/"
LICENSE = "CLOSED"

SRC_URI = "https://download.roonlabs.net/builds/RoonBridge_linuxarmv8.tar.bz2 \
           file://roonbridge.service"
SRC_URI[sha256sum] = "fa9af3123f7a47e7f5cf87b011315aac5e2863ab53d955bfba449381404b8989"

S = "${UNPACKDIR}"

inherit systemd

SYSTEMD_SERVICE:${PN} = "roonbridge.service"
SYSTEMD_AUTO_ENABLE = "enable"

# RoonBridge is a prebuilt binary for armv8
COMPATIBLE_MACHINE = "raspberrypi4-64|raspberrypi5"
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Skip QA checks for prebuilt binaries
INSANE_SKIP:${PN} = "already-stripped ldflags file-rdeps dev-so"

do_install() {
    # Install RoonBridge to /opt
    install -d ${D}/opt
    cp -r ${S}/RoonBridge ${D}/opt/

    # Make start.sh executable
    chmod +x ${D}/opt/RoonBridge/start.sh

    # Create data directory
    install -d ${D}/var/roon

    # Install systemd service
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/roonbridge.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} = "/opt/RoonBridge \
               /var/roon \
               ${systemd_system_unitdir}/roonbridge.service"

# Disable debug package generation
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
