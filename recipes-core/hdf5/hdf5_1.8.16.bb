DESCRIPTION = "Unique technology suite that makes possible the management of extremely large and complex data collections"
AUTHOR = "Alexander Leiva <norxander@gmail.com>"
SUMMARY = "HDF5 is a data model, library, and file format for storing and managing data"
HOMEPAGE = "http://caffe.berkeleyvision.org/"
LICENSE = "MIT"
PRIORITY= "optional"
SECTION = "libs"
PR = "r0"

RDEPENDS_${PN} = "zlib"

LIC_FILES_CHKSUM = "file://COPYING;md5=f1883baf3a14753e47d9152b3b994ada"

SRC_URI = "https://www.hdfgroup.org/ftp/HDF5/releases/${PN}-${PV}/src/${PN}-${PV}.tar.bz2	\
		file://fix-configure.patch;patch=1;pnum=0	\
		file://fix-test-make.patch;patch=1;pnum=0	\
		file://fix-src-make.patch;patch=1;pnum=0	\
		file://H5lib_settings.c				\
		file://H5Tinit.c				\
		"

SRC_URI[md5sum] = "79c1593573ebddf734eee8d43ecfe483"
SRC_URI[sha256sum] = "13aaae5ba10b70749ee1718816a4b4bfead897c2fcb72c24176e759aec4598c6"

S = "${WORKDIR}/${PN}-${PV}"

PACKAGES += "${PN}-extra"
FILES_${PN} += "/usr/lib/libhdf5.settings"
FILES_${PN}-extra = "/usr/share/hdf5_examples/"

do_configure_prepend () {
	find ./ -type f -print0 | xargs -0 sed -i 's/TEST_SCRIPT =.*/TEST_SCRIPT =/g'
}

do_configure () {
	./configure --host=${HOST_SYS} --build=${BUILD_SYS} --disable-sharedlib-rpath \
				--enable-production --enable-cxx --prefix=${D}/usr	\
				--with-zlib=${STAGING_INCDIR},${STAGING_LIBDIR}
}

do_qa_configure () {
}

do_install() {
	oe_runmake install
}

