#!/bin/sh

WORKSPACE_DIR=$1

APPLICATION_GUI_LOCATION="src/main/resources/public"
GENERATED_GUI_PROD_FOLDER_NAME="dist"
GENERATED_GUI_PROD_FOLDER="${APPLICATION_GUI_LOCATION}/${GENERATED_GUI_PROD_FOLDER_NAME}"
JENKINS_VARSOFT_DIR="/varsoft/jenkins"
DELIVERY_FOLDER="${JENKINS_VARSOFT_DIR}/available"
JENKINS_LOG_DIR="${JENKINS_VARSOFT_DIR}/logs"
GUI_NAME="Mediatheque-ui"
installationDate=$(date +'%Y/%m/%d %H:%M:%S')
OUTPUT_LOG_FILE="${JENKINS_LOG_DIR}/installation-${installationDate}.log"

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
	echo "${dateMesg} : [${msgPrefix}] ${msg}" >> ${OUTPUT_LOG_FILE}

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

installTools()
{

	cd "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}"

	printMessage "I" "Clean npm module"
	npm prune >> ${OUTPUT_LOG_FILE}
	if [ -d "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}/node_modules/" ]
	then
		rm -rf "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}/node_modules/"
		catchError $? "Failed to clean npm modules with return code $?"
	fi
	
	printMessage "I" "Start installation of NPM modules"
	npm install >> ${OUTPUT_LOG_FILE}
	catchError $? "Failed to install npm dependencies with return code $?"

	printMessage "I" "Start installation of Bower modules"
	bower install >> ${OUTPUT_LOG_FILE}
	catchError $? "Failed to install bower dependencies with $?"

}

build()
{
	grunt >> ${OUTPUT_LOG_FILE}
	catchError $? "Failed to build with return code $?"
}

generateGuiArchive()
{
	printMessage "I" "Generate archive file of ${GUI_NAME}"
	
	cd "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}"
	printMessage "I" "Generate archive file of ${GUI_NAME}.tar"
	
	tar cvf "${GUI_NAME}.tar" "${WORKSPACE_DIR}/${GENERATED_GUI_PROD_FOLDER}"
	catchError $? "Failed to generate tar of the gui"
	
	gzip "${GUI_NAME}.tar"
	catchError $? "Failed to compress the gui"

	printMessage "I" "Make GUI available in ${DELIVERY_FOLDER}/"
	mv "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}/${GUI_NAME}.tar.gz" "${DELIVERY_FOLDER}/"
	catchError $? "Failed to delivered the archive file of gui"
	printMessage "I" "GUI is now available in ${DELIVERY_FOLDER}/${GUI_NAME}.tar.gz"

}

###############################################################################
#
#			MAIN PART
#
###############################################################################

if [ -z "${WORKSPACE_DIR}" ]
then
	printMessage "E" "Missing working directory"
	printMessage "E" "Usage : package-gui.ksh <WORKING_DIR>"
	exit 1
fi

if [ ! -d ${WORKSPACE_DIR} ]
then
	printMessage "E" "The ${WORKSPACE_DIR} does not exist."
	exit 1
fi

installTools
build
generateGuiArchive

printMessage "I" "Fin de l'installation"
exit 0
