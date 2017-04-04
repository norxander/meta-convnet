SUMMARY = "Snappy is a compression/decompression library."
AUTHOR = "Alexander Leiva <norxander@gmail.com>"
DESCRIPTION = "Snappy is a compression/decompression library. It does not aim for maximum compression, \
				or compatibility with any other compression library; instead, \
				it aims for very high speeds and reasonable compression."
HOMEPAGE="http://google.github.io/snappy/"
SECTION = "console/utils"
PRIORITY= "optional"
LICENSE = "MIT"
PR = "r0"

LIC_FILES_CHKSUM = "file://COPYING;md5=f62f3080324a97b3159a7a7e61812d0c"

SRC_URI = "https://github.com/google/snappy/releases/download/${PV}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "7358c82f133dc77798e4c2062a749b73"
SRC_URI[sha256sum] = "2f1e82adf0868c9e26a5a7a3115111b6da7e432ddbac268a7ca2fae2a247eef3"

inherit autotools pkgconfig

