require recipes-core/images/camelpi-image.bb

SUMMARY = "Camel Audio test image with debugging and testing tools"

IMAGE_INSTALL:append = " \
    util-linux \
    lsof \
    mpc \
"
