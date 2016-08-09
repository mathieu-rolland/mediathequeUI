#!/bin/ksh

JAR_TO_DOWNLOAD="/varsoft/jenkins/available/Mediatheque*.jar"
DESTINATION_FOLDER="/varsoft/mediatheque/delivery"
FINAL_JAR_NAME="mediatheque-ui"

CURRENT_VERSION="mediatheque-ui.jar"
PRODUCT_DIR="/product/mediatheque"
DEPLOY_DATE=$(date +%Y%m%dT%H%M%S)

JAVA_JDK_BIN="/usr/lib/jvm/java-8-openjdk-armhf"

function getPackage
{
	scp "${OVH_USER}@${OVH_HOST}:${JAR_TO_DOWNLOAD}" "${DESTINATION_FOLDER}/${FINAL_JAR_NAME}.jar"
	ret=$?
	if [ "$ret" -ne 0 ]
	then
		echo "[ERROR] Failed to get package on ${JAR_TO_DOWNLOAD} on ${OVH_HOST}"
		exit 1
	fi
}


function deployPackage
{
	if [ -L "${PRODUCT_DIR}/${CURRENT_VERSION}" ]
	then
		echo "[INFO] Remove previous installation"
		rm -f "${PRODUCT_DIR}/${CURRENT_VERSION}"
	else
		echo "[INFO] No version already available"
	fi

	mv "${DESTINATION_FOLDER}/${FINAL_JAR_NAME}.jar" "${PRODUCT_DIR}/${FINAL_JAR_NAME}_${DEPLOY_DATE}.jar"
	ret=$?
	if [ "${ret}" -ne 0 ]
	then
		echo "[ERROR] Failed to deploy package ${FINAL_JAR_NAME}.jar"
		exit 2
	fi

	ln -s "${PRODUCT_DIR}/${FINAL_JAR_NAME}_${DEPLOY_DATE}.jar" "${CURRENT_VERSION}"
	ret=$?
	if [ "${ret}" -ne 0 ]
	then
		echo "[ERROR] Failed to create link to package ${FINAL_JAR_NAME}_${DEPLOY_DATE}.jar"
		exit 3
	fi

}

function startServer
{
	"${JAVA_JDK_BIN}/bin/java" -jar "${CURRENT_VERSION}"
	if [ "${ret}" -ne 0 ]
	then
		echo "[ERROR] Failed to start application ${CURRENT_VERSION}"
		exit 4
	fi
}

getPackage
deployPackage
startServer

