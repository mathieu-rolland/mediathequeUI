'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:SearchlocalmovietoallocineCtrl
 * @description
 * # SearchlocalmovietoallocineCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('SearchlocalmovietoallocineCtrl', [ '$scope' , '$location' , '$rootScope' , 'AllocineWebService' , 'localStorageService',
                                                  	function ( $scope , $location , $rootScope , AllocineWebService, localStorageService) {
	  
	  $scope.movies = {
			  list: []
	  };
		  
	  $scope.movie = $rootScope.movie;
	  
	  var alerts = $scope.alerts = [];
	  
	  $scope.addAlert = function(message, errorType) {
		console.log(message + ' => ' + errorType );
	    alerts.push({msg: message, type:errorType});
	  };
	  
	  $scope.closeAlert = function(index) {
	    $scope.alerts.splice(index, 1);
	  };
	  
	  var alloCineSearchCallback = function( response ){
		  	console.log( response );
			
			if( response === null ){
				return;
			}
			
			if( response.errorCode !== 0 ){
				console.log('An error occured');
				$scope.addAlert( response.errorDesc , 'danger' );
				return;
			}
			
			for(var i = 0 ; i < response.movies.length ; i++){
				var poster = response.movies[i].poster;
				if( poster !== undefined && poster.href === undefined){
					poster.href = 'images/yeoman.png';
				}
			}
			 
			$scope.movies.list = response.movies;
			
	  };
	  
	  var allocineSynchronizeCallback = function( response ){
		  console.log( response );
	  }
	  
	  console.log( 'Search movies in AlloCine API from disk : ' );
	  
	  
	  $scope.searchMovie = function( movie ){
		  AllocineWebService.searchMovie( movie.title , alloCineSearchCallback );
	  }
	  
	  $scope.synchronizeMovie = function( code ){
		  console.log('code : ' + code );
		  console.log( $scope.movie );
		  if( $scope.movie !== undefined ){
			  $scope.movie.code = code;
			  AllocineWebService.synchronizeMovie( allocineSynchronizeCallback , $scope.movie , code );
		  }
	  }
	  

	  if( $scope.movie !== undefined ){
		  localStorageService.set('localMovieSearch' , $scope.movie );
		  $scope.searchMovie( $scope.movie );
	  }else{
		  var searchMovie = localStorageService.get('localMovieSearch');
		  if( angular.isDefined(searchMovie) ){
			  console.log('Movie search : '); 
			  console.log( searchMovie );
			  $scope.movie = searchMovie;
			  $scope.searchMovie( searchMovie );
		  }
	  }
	  
 }]);
