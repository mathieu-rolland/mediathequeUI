#!/bin/sh

WORKSPACE_DIR=$1

APPLICATION_GUI_LOCATION="src/main/resources/public"
GENERATED_GUI_PROD_FOLDER_NAME="dist"
GENERATED_GUI_PROD_FOLDER="${APPLICATION_GUI_LOCATION}/${GENERATED_GUI_PROD_FOLDER_NAME}"
DELIVERY_FOLDER="/varsoft/jenkins/available"
GUI_NAME="Mediatheque-ui"

printMessage()
{
	msgType="${1}"
	msg="${2}"
	dateMesg=$(date +'%Y/%m/%d %H:%M:%S')
	case "${msgType}" in
		'I') echo "${dateMesg} : [INFO] ${msg}"
		;;
		'E') echo "${dateMesg} : [ERROR] ${msg}"
		;;
		'W') echo "${dateMesg} : [WARNING] ${msg}"
		;;
		*) echo "${dateMesg} : [UNKOWN] ${msg}"
		;;
	esac

}

catchError()
{
	returnValue="${1}"
	message="${2}"

	if [ "${returnValue}" -ne 0 ]
	then
		printMessage "E" "${message}"
		exit 1
	fi

}

installTools()
{

	cd "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}"

	printMessage "I" "Clean npm module"
	npm prune
	if [ -d "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}/node_modules/" ]
	then
		rm -rf "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}/node_modules/"
		catchError $? "Failed to clean npm modules with return code $?"
	fi
	
	printMessage "I" "Start installation of NPM modules"
	npm install
	catchError $? "Failed to install npm dependencies with return code $?"

	printMessage "I" "Start installation of Bower modules"
	bower install
	catchError $? "Failed to install bower dependencies with $?"

}

build()
{
	grunt
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
