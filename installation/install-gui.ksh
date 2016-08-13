#!/bin/sh

REMOTE_JENKINS_VARSOFT_DIR="/varsoft/jenkins"
REMOTE_AVAILABLE_DIRECTORY="${REMOTE_JENKINS_VARSOFT_DIR}/available"
GUI_FOLDER_NAME="mediatheque-ui"
REMOTE_PACKAGE_AVAILABLE="${REMOTE_AVAILABLE_DIRECTORY}/${GUI_FOLDER_NAME}.tar.gz"
PRODUCT_DIR="/product"
MEDIATHEQUE_DIR="${PRODUCT_DIR}/mediatheque"
MEDIATHQUE_GUI="mediatheque-ui"
EXEC_DATE=$(date +'%Y%m%d%H%M%S')

printMessage()
{
	msgType="${1}"
	msg="${2}"
	dateMesg=$(date +'%Y/%m/%d %H:%M:%S')
	msgPrefix=""
	case "${msgType}" in
		'I') msgPrefix="INFO"
		;;
		'E') msgPrefix="ERROR"
		;;
		'W') msgPrefix="WARNING"
		;;
		*) msgPrefix="UNKOWN"
		;;
	esac

	echo "${dateMesg} : [${msgPrefix}] ${msg}"

}

catchError()
{
	returnValue="${1}"
	message="${2}"

	if [ "${returnValue}" -ne 0 ]
	then
		printMessage "E" "${message}"
		printMessage "E" "See log in ${OUTPUT_LOG_FILE}"
		exit 1
	fi

}

checkEnvironment()
{
	if [ -z "${OVH_HOST}"]
	then
		printMessage "E" "OVH_HOST is not defined"
		exit 1
	fi

	if [ -z "${OVH_USER}"]
	then
		printMessage "E" "OVH_USER is not defined"
		exit 1
	fi

}

getPackage()
{
	printMessage "I" "Fetch package on host ${OVH_HOST}"
	scp "${OVH_USER}@${OVH_HOST}:${REMOTE_PACKAGE_AVAILABLE}" "${MEDIATHEQUE_DIR}/${MEDIATHQUE_GUI}.tar.gz"
	catchError "${?}" "Fail to fetch package on host ${OVH_HOST}"
}

deployPackage()
{
	printMessage "I" "Deploy package"
	cd "${MEDIATHEQUE_DIR}"
	tar xvf "${MEDIATHEQUE_DIR}/${MEDIATHQUE_GUI}.tar.gz"
}

archiveCurrentRelease()
{
	printMessage "I" "Update current version of mediatheque-ui"
	if [ -d "${MEDIATHEQUE_DIR}/${MEDIATHQUE_GUI}" ]
	then
		mv "${MEDIATHEQUE_DIR}/${MEDIATHQUE_GUI}" "${MEDIATHEQUE_DIR}/${MEDIATHQUE_GUI}_${EXEC_DATE}"
		catchError "$?" "Failed to archive current release with return code $?"
	fi
}

##############################################################################################
#
#			MAIN PART
#
##############################################################################################

checkEnvironment
archiveCurrentRelease
getPackage
deployPackage

printMessage "I" "Installation terminée"