'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:MymoviesCtrl
 * @description
 * # MymoviesCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp').controller('MymoviesCtrl',[ '$scope' , 'AllocineWebService' , function ( $scope , AllocineWebService) {
	
	var myMovieCallback = function(response){
		console.log(response);
		 $scope.movies = {
				  list: response.movies
		  };
	};
	
	AllocineWebService.loadMyMovie( myMovieCallback );
	
	$scope.formatDuration = function( duration ){
		"rien"
	}
	
}]);
