require recipes-core/images/auris-image.bb

SUMMARY = "Auris test image with debugging and testing tools"

IMAGE_INSTALL:append = " \
    util-linux \
    lsof \
    mpc \
"
