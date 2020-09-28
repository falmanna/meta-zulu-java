
PV = "8.0.265"
PV_UPDATE = "131"
BUILD_NUMBER = "8.48.3.246"
SUFFIX = "linux_aarch32hf"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the 32 bit ARM architecture from \
 Azul Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against \
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

LICENSE = "GPL-2.0-with-classpath-exception"
LIC_FILES_CHKSUM = "file://zulu${BUILD_NUMBER}-ca-jdk${PV}-${SUFFIX}/LICENSE;md5=3e0b59f8fac05c3c03d4a26bbda13f8f"

SRC_URI="http://cdn.azul.com/zulu-embedded/bin/zulu${BUILD_NUMBER}-ca-jdk${PV}-${SUFFIX}.tar.gz"

SRC_URI[md5sum] = "64114fe973a43faf44b4870bcfeb79ac"
SRC_URI[sha256sum] = "210f82991014fb0913133eb3433b370201d95a48c11ac4ace017bb59846feba8"

PR = "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/zulu${BUILD_NUMBER}
  cp -a ${S}/zulu${BUILD_NUMBER}-ca-jdk${PV}-${SUFFIX}/* ${D}${datadir}/zulu${BUILD_NUMBER}
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/zulu${BUILD_NUMBER}/bin/java ${D}${bindir}/java
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "zulu-jdk"
PROVIDES += "virtual/java"

DEPENDS = "alsa-lib libxi libxrender libxtst"

