FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://usb-audio.cfg \
                   file://initramfs.cfg \
"

# When using initramfs bundle, ensure bootimg-partition uses the kernel with embedded initramfs
do_deploy:append() {
    if [ "${INITRAMFS_IMAGE_BUNDLE}" = "1" ]; then
        # After kernel deployment, make Image point to Image-initramfs
        # so bootimg-partition will use the correct kernel with embedded initramfs
        cd ${DEPLOYDIR}

        # Remove the old Image symlink/file
        rm -f Image-${MACHINE}.bin Image

        # Create new symlink to Image-initramfs
        ln -sf Image-initramfs-${MACHINE}.bin Image-${MACHINE}.bin
        ln -sf Image-initramfs-${MACHINE}.bin Image
    fi
}
