#!/bin/sh

WORKSPACE_DIR=$1

APPLICATION_GUI_LOCATION="src/main/resources/public/"

if [ ! -d ${WORKSPACE_DIR} ]
then
	echo "[ERROR] The ${WORKSPACE_DIR} does not exist."
	exit 1
fi

installTools()
{
	cd "${WORKSPACE_DIR}/${APPLICATION_GUI_LOCATION}"
	npm install
	ret=$?
	if [ "${ret}" -ne 0 ]
	then
		echo "[ERROR] Failed to install npm dependencies"
		exit 2
	fi

	bower install
	ret=$?
	if [ "${ret}" -ne 0 ]
	then
		echo "[ERROR] Failed to install bower dependencies"
		exit 3
	fi

}

build()
{
	grunt
	ret=$?
	if [ "${ret}" -ne 0 ]
	then
		echo "[ERROR] Failed to build"
		exit 3
	fi
}

installTools
build

echo "[INFO] Fin de l'installation"
exit 0
