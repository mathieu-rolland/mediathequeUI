'use strict';

function delayBetweenDates( date1 , date2 , mesure ){
	
	var beginDate, endDate, timeDiff;
	
	if( date1.getTime() > date2.getTime() ){
		beginDate = date2; endDate = date1;
	}else{
		beginDate = date1; endDate = date2;
	}

	timeDiff = endDate.getTime() - beginDate.getTime();
	
	switch( mesure ){
		
	case 'day':
		return parseInt( timeDiff / ( 1 * 24 * 60 * 60 * 1000 ) );
	case 'hour':
		return parseInt( timeDiff / ( 1 * 60 * 60 * 1000 ) );
	case 'minute':
		return parseInt( timeDiff / ( 1 * 60 * 60 * 1000 ) );
	default: //ms
		return timeDiff;	
	
	}
	
}

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:LoadfromdiskCtrl
 * @description
 * # LoadfromdiskCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('LoadfromdiskCtrl', [ '$scope' , '$location', '$rootScope' , 'AllocineWebService', 'localStorageService' ,  function ( $scope , $location , $rootScope , AllocineWebService, localStorageService ) {

	  var responseCallBack = function( response ){
		  $scope.movies.list = response.movies;
		  localStorageService.set('searchOnDisk-result' , response.movies );
		  localStorageService.set( 'searchOnDisk-lastDate' , new Date().getTime() );
		  $scope.pageLoaded = true;
		  console.log( response.movies );
	  };
	  
	  
	  $scope.searchOnDisk = function( search )
	  {
		    $scope.pageLoaded = false;
			console.log('search : ' + search );
			localStorageService.set('search-path' , search );
			if( $scope.selectedMachine === 'Local' ){
				console.log( AllocineWebService.loadDisk( search , responseCallBack) );
			}else{
				console.log("Use FTP query to load movie");
				AllocineWebService.listMoviesOnMachines( $scope.selectedMachine, search , responseCallBack );
			}
	  };
	  
	  $scope.searchMovieInAllocine = function( movie ){
		  $rootScope.movie = movie;
		  $location.path('/load-disk/search');
	  };
	  
	  var machineResponseCallback = function( response ){
		  if( response.errorCode === 0 ){
			  $scope.machines = response.data;
			  console.log("Machines list is");
			  console.log( response.data );
		  }else{
			  console.log( "Error " + response.errorDesc );
		  }
	  }
	  
	  /*Get availabale machines*/
	  $scope.getMachines = function(){
		  AllocineWebService.listAllMachines( machineResponseCallback );
	  };
	  
	  $scope.selectedMachine = {
			  "ip":"Local"
	  };
	  
	  $scope.selectMachine = function(machine){
		  if(machine === "Local" ){
			  $scope.selectedMachine = {
					  "ip":"Local"
			  };
		  }else{
			  $scope.selectedMachine = machine;
		  }
	  };
	  
	  /*Load machines*/
	  $scope.getMachines();
	  
	  /*Find last search : */
	  var lastPathSearch = localStorageService.get('search-path');
	  if( lastPathSearch !== undefined ){
		  console.log('Previous search : ' + lastPathSearch );
		  $scope.movies = {
			path : lastPathSearch
		  };
	  }
	  
	  /*Load movies load in CSV file*/
	  if( angular.isDefined( $rootScope.movieFromCsv ) ){
		  $scope.movies.list = $rootScope.movieFromCsv;
		  $scope.pageLoaded = true;
		  $scope.movies.path =  $rootScope.csvUploadedName;
	  }else{
	  
		  var cacheInCookie = localStorageService.get( 'searchOnDisk-result' );
		  var lastDateCacheUpdated = localStorageService.get( 'searchOnDisk-lastDate' );

		  if( angular.isDefined(cacheInCookie) && 
				  cacheInCookie !== null  && 
				  lastDateCacheUpdated !== null && 
				  delayBetweenDates( new Date() , new Date(lastDateCacheUpdated) , 'day') < 1 ){
			  $scope.movies.list = cacheInCookie;
			  $scope.pageLoaded = true;
		  }else{
			  $scope.searchOnDisk( lastPathSearch );
		  }
	  }
 }]);
