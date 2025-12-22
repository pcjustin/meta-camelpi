FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://usb-audio.cfg \
                   file://initramfs.cfg \
"
