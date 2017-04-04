DESCRIPTION = "The gflags package contains a C++ library that implements commandline flags processing"
SUMMARY = "Google Commandline Flags"
AUTHOR = "Alexander Leiva <norxander@gmail.com>"
HOMEPAGE = "https://gflags.github.io/gflags/"
PRIORITY= "optional"
SECTION = "libs"
LICENSE = "MIT"
PR = "r0"

RDEPENDS_${PN} = "bash"

LIC_FILES_CHKSUM = "file://COPYING.txt;md5=c80d1a3b623f72bb85a4c75b556551df"

SRCREV = "f0523f14a93cbb46fff9b318508aa1c6923158c7"
SRC_URI = "git://github.com/gflags/gflags.git;branch=master"

S = "${WORKDIR}/git"

EXTRA_OECMAKE = "-DBUILD_SHARED_LIBS:BOOL=ON	\
				 -DBUILD_gflags_LIB:BOOL=ON		\
				 -DINSTALL_HEADERS:BOOL=ON		\
				 "
do_install_append() {
	rm -rf ${D}/usr/lib/cmake
}

inherit cmake

