SUMMARY = "C++ library for building UPnP applications"
DESCRIPTION = "libupnpp is a C++ interface to UPnP services, built on top of libnpupnp"
HOMEPAGE = "http://www.lesbonscomptes.com/upnpp/"
LICENSE = "LGPL-2.1-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=321bf41f280cf805086dd5a720b37785"

DEPENDS = "libnpupnp curl expat util-linux pkgconfig-native"

SRC_URI = "https://www.lesbonscomptes.com/upmpdcli/downloads/libupnpp-${PV}.tar.gz"
SRC_URI[sha256sum] = "d3b201619a84837279dc46eeb7ccaaa7960d4372db11b43cf2b143b5d9bd322e"

S = "${UNPACKDIR}/libupnpp-${PV}"

inherit meson

FILES:${PN} = "${libdir}/libupnpp.so.* \
               ${libdir}/libupnpp-*.so"
FILES:${PN}-dev = "${includedir}/libupnpp ${libdir}/libupnpp.so ${libdir}/libupnpp.a ${libdir}/pkgconfig/libupnpp.pc"
