'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:MymoviesCtrl
 * @description
 * # MymoviesCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp').controller('MymoviesCtrl',[ '$scope' , 'AllocineWebService' , 'localStorageService' , function ( $scope , AllocineWebService, localStorageService) {
	
	var myMovieCallback = function(response){
		console.log(response);
		 $scope.movies = {
				  list: response.movies
		  };
		$scope.pageLoaded = true;
	};
	
	AllocineWebService.loadMyMovie( myMovieCallback );
	
	$scope.changeSort = function( sortBy ){
		
		localStorageService.set('my-movie-sort' , sortBy );
		$scope.sortKey=sortBy;
		
		switch( sortBy ){
			case 'title':
				$scope.sortBy='Titre';
				break;
			case 'year' :
				$scope.sortBy='Date de sortie';
				break;
			default:
				$scope.sortKey='title';
				$scope.sortBy='Titre';
				break;
		}
		
	};
	
	var previousSort = localStorageService.get('my-movie-sort');
	if( angular.isDefined(previousSort) ){
		$scope.changeSort( previousSort );
	}
	
	
}]);
