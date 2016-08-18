'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:CsvuploadCtrl
 * @description
 * # CsvuploadCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('CsvuploadCtrl', function ( $scope , AllocineWebService , localStorageService , $rootScope, $location ) {
    
	  $scope.uploadFile = function(args){
		  
		  console.log( 'Start to upload ' + $scope.picFile.name );
		  console.log($scope.picFile);
		  
		  var fileUploadCallback = function( response ){
			  if(  angular.isDefined( response )
					  &&  angular.isDefined( response.data )){
				  
				  if( response.data.length === 0 ){
					  console.log('No movie generated');
				  }else{
					  console.log('as data');
					  $rootScope.searchMovieFromCsv = response.data;
					  $location.path('/load-disk');
				  }
			  }
		  }
		  
		  AllocineWebService.uploadCSVMovies( $scope.picFile , fileUploadCallback );
		  
	  }
	  
	  
	  
  });
