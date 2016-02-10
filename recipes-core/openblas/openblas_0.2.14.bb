DESCRIPTION = "OpenBLAS is an optimized BLAS library based on GotoBLAS2 1.13 BSD version."
SUMMARY = "OpenBLAS : An optimized BLAS library"
AUTHOR = "Alexander Leiva <norxander@gmail.com>"
HOMEPAGE = "http://www.openblas.net/"
PRIORITY= "optional"
SECTION = "libs"
LICENSE = "BSD"
PR = "r0"

DEPENDS = "make"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5adf4792c949a00013ce25d476a2abc0"

SRCREV = "3684706a121f9d9e1ccfc4a2bbb98f698eb04514"
SRC_URI = "git://github.com/xianyi/OpenBLAS.git;branch=develop"

S = "${WORKDIR}/git"

do_compile () {
	oe_runmake HOSTCC="${BUILD_CC}"						\
  			   CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" 	\
			   ONLY_CBLAS=1 BINARY=32 TARGET=CORTEXA9
}

do_install() {
	oe_runmake HOSTCC="${BUILD_CC}" 					\
  			   CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" 	\
			   ONLY_CBLAS=1 BINARY=32 TARGET=CORTEXA9		\
			   PREFIX=${D}/usr install
}

do_install_append() {
	rm -rf ${D}/usr/bin
	rm -rf ${D}/usr/lib/cmake
}

FILES_${PN}     = "${libdir}/*"
FILES_${PN}-dev = "${includedir} ${libdir}/lib${PN}.so"

