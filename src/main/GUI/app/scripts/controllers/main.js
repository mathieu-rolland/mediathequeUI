'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp').controller('MainCtrl', [ '$scope', 'AllocineWebService', function ( $scope , AllocineWebService) {

	var responseCallBack = function( response ){
		
		console.log( response );
		
		if( response === null ){
			return;
		}
		
		if( response.errorCode !== 0 ){
			console.log('An error occured');
			return;
		}
		
		$scope.movies.list = response.movies;
		
		
	};
	
	$scope.searchMovie = function(search){
		console.log('search : ' + search );
		console.log( AllocineWebService.searchMovie( search , responseCallBack) );
	};
	
	
	
}]);
