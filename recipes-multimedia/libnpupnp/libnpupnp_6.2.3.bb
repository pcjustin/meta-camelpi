SUMMARY = "Lightweight UPnP library implementation"
DESCRIPTION = "libnpupnp is a lightweight C++ UPnP library, base for libupnpp"
HOMEPAGE = "http://www.lesbonscomptes.com/npupnp/"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=394a0f17b97f33426275571e15920434"

DEPENDS = "curl expat util-linux libmicrohttpd pkgconfig-native"

SRC_URI = "https://www.lesbonscomptes.com/upmpdcli/downloads/libnpupnp-${PV}.tar.gz"
SRC_URI[sha256sum] = "563d2a9e4afe603717343dc4667c0b89c6a017008ac6b52262da17a1e4f6bb96"

S = "${UNPACKDIR}/libnpupnp-${PV}"

inherit meson

FILES:${PN} = "${libdir}/libnpupnp.so.* \
               ${libdir}/libnpupnp-*.so"
FILES:${PN}-dev = "${includedir}/npupnp ${libdir}/libnpupnp.so ${libdir}/libnpupnp.a ${libdir}/pkgconfig/libnpupnp.pc"
