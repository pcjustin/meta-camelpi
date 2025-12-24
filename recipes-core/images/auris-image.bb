require recipes-core/images/core-image-minimal.bb

SUMMARY = "Auris audio WIC image (rootfs not used - initramfs boot only)"

# This image is used only to generate the WIC file structure
# The actual rootfs comes from auris-initramfs-image
