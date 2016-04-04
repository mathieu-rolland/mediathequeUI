'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:LoadfromdiskCtrl
 * @description
 * # LoadfromdiskCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('LoadfromdiskCtrl', [ '$scope', 'AllocineWebService', function ( $scope , AllocineWebService) {

	  var responseCallBack = function( response ){
		  console.log( response );
		  $scope.movies.list = response.movies;
	  };
	  
	  $scope.searchOnDisk = function( search )
	  {
			console.log('search : ' + search );
			console.log( AllocineWebService.loadDisk( search , responseCallBack) );
	  };
	  	  
 }]);
