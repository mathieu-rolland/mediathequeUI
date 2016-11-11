'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:MymoviesCtrl
 * @description
 * # MymoviesCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp').controller('MymoviesCtrl',[ '$scope' , '$sce' , 'AllocineWebService' , 'localStorageService' , function ( $scope , $sce ,  AllocineWebService, localStorageService) {
	
	$scope.playIsDemand = false;
	
	var myMovieCallback = function(response){
		console.log(response);
		 $scope.movies = {
				  list: response.movies
		  };
		$scope.pageLoaded = true;
		
		if( response.errorCode !== 0 ){
			$scope.addAlert( response.errorDesc , 'danger' );
		}
		
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
	if( angular.isDefined( previousSort ) ){
		$scope.changeSort( previousSort );
	}
	
	$scope.addAlert = function(message, errorType) {
		console.log(message + ' => ' + errorType );
	    $scope.alerts.push({msg: message, type:errorType});
	    console.log(alerts);
	};
	
	$scope.closeAlert = function(index) {
	    $scope.alerts.splice(index, 1);
	};
	
	//start module: 
	var alerts = $scope.alerts = [];
	var videoApi;
	
	$scope.displayInPopup = function(movie){
		if( angular.isUndefined( $scope.selectedMovie ) ){
			$scope.selectedMovie = movie;
			$scope.isSelectedMovie = true;
		}
	}
	
	$scope.closePlayer = function(){
		$scope.isSelectedMovie = false;
		$scope.playIsDemand = false;
		$scope.selectedMovie = undefined;
		if ( angular.isDefined(videoApi) ){
			videoApi.pause();
		} else{
			console.log( "Video API is not ready" );
		}
	}
	
	$scope.closePopup = function(){
		console.log('Close popup!');
		$scope.selectedMovie = undefined;
		$scope.isSelectedMovie = false;
	}
	
	$scope.onPlayerReady = function(API) {
		videoApi = API;
		console.log(videoApi);
    };
	
	/*Composent video*/
	$scope.readMovie = function(){
		console.log($scope.selectedMovie);
		$scope.playIsDemand = true;
		$scope.isSelectedMovie = false;
		if( angular.isDefined( $scope.selectedMovie ) ){
			$scope.config = {
					sources: [
						{src: $sce.trustAsResourceUrl("assets/" + "Mr Robot S02E05 Truefrench WEBrip 720p x264 AAC SBZ.mp4" /*$scope.selectedMovie.namedInCache*/ ), type: /*$scope.selectedMovie.videoType*/ 'video/mp4' }
					],
					theme: "bower_components/videogular-themes-default/videogular.css",
					plugins: {
						poster: $scope.selectedMovie.poster.href,
						controls: {
	                        autoHide: true,
	                        autoHideTime: 50
	                    }
					}
				};
			console.log($sce);
		}
		
		if( videoApi != null ){
			//videoApi.play();
		}
		
	}
	
}]);
