DESCRIPTION = "convnet-demo apps image"
AUTHOR = "Alexander Leiva <norxander@gmail.com>"
HOMEPAGE = "https://github.com/norxander"
LICENSE = "BSD"

inherit core-image

CORE_IMAGE_EXTRA_INSTALL += "		\
			less		\
			perf		\
			nano		\
			file		\
			caffe		\
			caffe-dev	\
			kernel-dev	\
			openssh		\
			powertop	\
			oprofile	\
			latencytop	\
			"

IMAGE_INSTALL = "						\
			packagegroup-caffe			\
			packagegroup-core-boot			\
			packagegroup-core-ssh-openssh		\
			packagegroup-core-buildessential	\ 
			${ROOTFS_PKGMANAGE_BOOTSTRAP}		\ 
			${CORE_IMAGE_EXTRA_INSTALL}		\
			"

IMAGE_FEATURES += "					\
			package-management		\
			ssh-server-openssh		\
			"

IMAGE_LINGUA = " "
ACCEPT_FSL_EULA = ""
LICENSE_FLAGS_WHITELIST = "commercial_libav commercial_x264"

export IMAGE_BASENAME = "convnet-demo-image"

