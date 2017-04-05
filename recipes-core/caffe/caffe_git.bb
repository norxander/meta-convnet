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

SRCREV = "4ba654f5c88c36ee8ba53964b7faf25c6d7010b4"
SRC_URI = "git://github.com/BVLC/caffe.git;branch=master		\
           file://0001-Allow-setting-numpy-include-dir-from-outside.patch \
			"
S = "${WORKDIR}/git"

FILES_${PN} += " \
    ${prefix}/python/* \
"
FILES_${PN}-dev = " \
    ${includedir} \
    ${datadir}/Caffe/*cmake \
    ${libdir}/*.so \
"

inherit cmake python-dir

# allow cmake to find native Python interpreter
OECMAKE_FIND_ROOT_PATH_MODE_PROGRAM = "BOTH"

EXTRA_OECMAKE = " \
    -DBLAS=open \
    -DPYTHON_NUMPY_INCLUDE_DIR=${STAGING_DIR_TARGET}${PYTHON_SITEPACKAGES_DIR}/numpy/core/include \
"

