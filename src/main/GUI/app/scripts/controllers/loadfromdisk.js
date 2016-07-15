'use strict';

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
		  console.log( response );
		  $scope.movies.list = response.movies;
	  };
	  
	  
	  $scope.searchOnDisk = function( search )
	  {
			console.log('search : ' + search );
			localStorageService.set('search-path' , search );
			console.log( AllocineWebService.loadDisk( search , responseCallBack) );
	  };
	  
	  $scope.searchMovieInAllocine = function( movie ){
		  $rootScope.movie = movie;
		  $location.path('/load-disk/search');
	  };
	  
	  /*Find last search : */
	  var lastPathSearch = localStorageService.get('search-path');
	  if( lastPathSearch != undefined ){
		  console.log('Previous search : ' + lastPathSearch );
		  $scope.movies = {
			path : lastPathSearch
		  }
	  }
	  
	  $scope.searchOnDisk( lastPathSearch );
	  
 }]);
