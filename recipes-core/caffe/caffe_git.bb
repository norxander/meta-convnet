DESCRIPTION = "Build Caffe library for CNN using OpenBLAS lib"
AUTHOR = "Alexander Leiva <norxander@gmail.com>"
SUMMARY = "Caffe : A fast open framework for deep learning"
HOMEPAGE = "http://caffe.berkeleyvision.org/"
LICENSE = "MIT"
PRIORITY= "optional"
SECTION = "libs"
PR = "r0"

DEPENDS = "make boost openblas protobuf glog gflags hdf5 opencv lmdb snappy leveldb python python-numpy"

LIC_FILES_CHKSUM = "file://LICENSE;md5=89a82623447e1b9b66e59b1e3c8a97fc"

SRCREV = "ca4e3428bd40ba5b4ba941a7781d0d16d0726005"
SRC_URI = "git://github.com/BVLC/caffe.git;branch=master		\
			file://mnist_convert_data.patch;patch=1;pnum=0		\
			file://Makefile.patch;patch=1;pnum=0				\
			file://db_lmdb.patch;patch=1;pnum=0					\
			"
S = "${WORKDIR}/git"

FILES_${PN} += "${libdir}/*.so"
FILES_${PN}-dev = "${includedir}"

do_configure (){
}

do_compile_prepend () {
    if [ ! -e ${S}/Makefile.config ]; then
        cat ${S}/Makefile.config.example > ${S}/Makefile.config
    fi
    
    sed -i -e "s|# CPU_ONLY := 1|CPU_ONLY := 1|g;					\
    		   s|Q ?= @|# Q ?= @|g;									\
    		   s|BLAS := atlas|BLAS := open|g;						\
    		   s|python2.7/dist-packages|python2.7/site-packages|g	\
    		   " ${S}/Makefile.config
    sed -i -e "s|/usr/lib|${STAGING_LIBDIR}|g" ${S}/Makefile.config
    sed -i -e "s|/usr/local/lib|${STAGING_LIBDIR}|g" ${S}/Makefile.config
    sed -i -e "s|/usr/include|${STAGING_INCDIR}|g" ${S}/Makefile.config
    sed -i -e "s|/usr/local/include|${STAGING_INCDIR}|g" ${S}/Makefile.config
}

do_compile () {
	oe_runmake distribute
	oe_runmake test
}

do_install() {
	install -m 0755 -d ${D}${libdir} 					\
						${D}${bindir} 					\
						${D}${includedir}/caffe/proto 	\
						${D}${includedir}/caffe/test 	\
						${D}${includedir}/caffe/util
	install -m 0755 -D distribute/bin/*.bin ${D}${bindir}
	install -m 0755 -D distribute/lib/* ${D}${libdir}
	install -m 0755 -D distribute/include/caffe/*.hpp ${D}${includedir}/caffe
	install -m 0755 -D distribute/include/caffe/proto/* ${D}${includedir}/caffe/proto
	install -m 0755 -D distribute/include/caffe/test/* ${D}${includedir}/caffe/test
	install -m 0755 -D distribute/include/caffe/util/* ${D}${includedir}/caffe/util
}

do_install_append () {
	rm -rf ${D}${bindir}/*.txt
}

