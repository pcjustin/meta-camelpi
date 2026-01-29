require recipes-core/images/core-image-minimal.bb

SUMMARY = "Camel Audio initramfs image - the actual rootfs for RAM boot"

# Increase initramfs size limit to accommodate all kernel modules
# Pi 5 has 8GB RAM, so 1GB initramfs is acceptable (1/8 of total RAM)
INITRAMFS_MAXSIZE = "1048576"

# This is the actual rootfs that gets packed as cpio.gz and loaded as initramfs
IMAGE_INSTALL:append = " \
    alsa-lib \
    alsa-utils \
    alsa-config \
    audio-detect \
    irq-affinity \
    usb-tuning \
    sysctl-tuning \
    mpd-camel \
    upmpdcli \
    shairport-sync \
    avahi-daemon \
    libconfig \
    scx \
    roonbridge \
    kernel-modules \
"
