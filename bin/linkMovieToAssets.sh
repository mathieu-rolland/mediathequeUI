#!/bin/sh

NAS_LOCATION="/ftp/nas_local"

SSA_NAME="mediatheque"
PRODUCT_DIR="/product/$SSA_NAME"
CATALOG_DIR="/catalog/$SSA_NAME"
VARSOFT_DIR="/varsoft/$SSA_NAME"

GUI_DIR="$PRODUCT_DIR/GUI"
ASSETS_LOCATION="$GUI_DIR/app/assets"
LOG_DIR="$VARSOFT_DIR/logs"
DEMANDS_LOCATION="$CATALOG_DIR/demands"

CURRENT_DATE=$(date '+%Y%m%d')
LOG_FILE="$LOG_DIR/link_demands_$CURRENT_DATE.log"

log_message()
{
	LOG_LEVEL="$1"
	LOG_PREFIX="$2"
	LOG_MESSAGE="$3"

	echo "$LOG_PREFIX [$LOG_LEVEL] : $LOG_MESSAGE" >> $LOG_FILE

}

log_info()
{	
	DATE=$(date '+%Y/%m/%d %H:%M:%S')
	NAME=$(basename	$0 .sh)
	MESSAGE=$1
	
	log_message "INFO" "$NAME - $DATE" "$MESSAGE"

}

log_error()
{	
	DATE=$(date '+%Y/%m/%d %H:%M:%S')
	NAME=$(basename	$0 .sh)
	MESSAGE=$1
	
	log_message "ERROR" "$NAME - $DATE" "$MESSAGE"

}

catch_error()
{
	return_code=$1
	error_message=$2
	
	if [ "${return_code}" -ne 0 ]
	then
		log_error "$error_message"
		exit 1
	fi
}

getWaitingDemand()
{

	nb_demand=0
	
	for demands in $(ls -t $DEMANDS_LOCATION/*.dem 2>/dev/null)
	do
		log_info "Traitement de la demande : $demands"
		
		origin_file=$(cut -d';' -f1 $demands)	
		
		target_movie_name=$(echo $origin_file | rev | cut -d'/' -f1 | rev)
		ln -s "${NAS_LOCATION}/${origin_file}" "${ASSETS_LOCATION}/${target_movie_name}"
		catch_error $? "Echec de traitement de la demande $demand"
		
		log_info "Demande $demands traitée avec succes. Le film $target_movie_name est disponible à la visualisation."
		
		rm $demands
		catch_error $? "Echec de suppression de la demande $demands"
		nb_demand=$(expr $nb_demand + 1)
		
	done 
	
	if [ "${nb_demand}" -ne 0 ]
	then
		log_info "Nombre de demande traité : $nb_demand"
	else
		log_info "Aucune demande a traiter."
	fi
	
}

getWaitingDemand

exit 0