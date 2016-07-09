'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:LoadfromdiskCtrl
 * @description
 * # LoadfromdiskCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('LoadfromdiskCtrl', [ '$scope' , '$location', '$rootScope' , 'AllocineWebService' , function ( $scope , $location , $rootScope , AllocineWebService) {

	  var responseCallBack = function( response ){
		  console.log( response );
		  $scope.movies.list = response.movies;
	  };
	  
	  
	  $scope.searchOnDisk = function( search )
	  {
			console.log('search : ' + search );
			console.log( AllocineWebService );
			console.log( AllocineWebService.loadDisk( search , responseCallBack) );
	  };
	  
	  $scope.searchMovieInAllocine = function( movie ){
		  $rootScope.movie = movie;
		  $location.path('/load-disk/search');
	  };
	  
 }]);
