#!/bin/ksh

REMOTE_JENKINS_VARSOFT_DIR="/varsoft/jenkins"
REMOTE_AVAILABLE_DIRECTORY="${REMOTE_JENKINS_VARSOFT_DIR}/available"
GUI_FOLDER_NAME="Mediatheque-ui"
REMOTE_PACKAGE_AVAILABLE="${REMOTE_AVAILABLE_DIRECTORY}/${GUI_FOLDER_NAME}.tar.gz"

function printMessage
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
	echo "${dateMesg} : [${msgPrefix}] ${msg}" >> ${OUTPUT_LOG_FILE}

}

function catchError
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

function getPackage
{

}

function deployPackage
{

}

function updateCurrentRelease
{

}

##############################################################################################
#
#			MAIN PART
#
##############################################################################################

getPackage
deployPackage
updateCurrentRelease

printMessage "I" "Installation terminée"