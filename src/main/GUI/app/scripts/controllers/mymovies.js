'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:MymoviesCtrl
 * @description
 * # MymoviesCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp').controller('MymoviesCtrl',[ '$scope' , '$sce' , 'AllocineWebService' , 'localStorageService' , '$document' , function ( $scope , $sce ,  AllocineWebService, localStorageService, $document) {
	
	$scope.playIsDemand = false;
	
	var defaultSearchText = 'Rechercher';
	
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
		$scope.selectedMovie = undefined;
		$scope.isSelectedMovie = false;
	}
	
	$scope.onPlayerReady = function(API) {
		videoApi = API;
    };
	
	/*Composent video*/
	$scope.readMovie = function(){
		
		var fileName = $scope.selectedMovie.path.split('/').reverse()[0];
		
		console.log( "Start to read movie " + fileName );
		
		$scope.playIsDemand = true;
		$scope.isSelectedMovie = false;
		if( angular.isDefined( $scope.selectedMovie ) ){
			$scope.config = {
					sources: [
							{
								src: $sce.trustAsResourceUrl("assets/Films/" + fileName ),
								type: 'video/mp4'
							}
					],
					theme: "bower_components/videogular-themes-default/videogular.css",
					plugins: {
						poster: $scope.selectedMovie.poster.href,
						controls: {
	                        autoHide: true,
	                        autoHideTime: 2000
	                    },
	                    autohideCursor: {
	                    	enabled: true,
	                    	time: 2000
	                    }
					}
				};
			console.log($scope.config);
		}
		
		if( videoApi != null ){
			//videoApi.play();
		}
		
	}
	
	angular.element(window).on('mousewheel', function(event){
		//if popup displayed : stop scroll
		if( $scope.playIsDemand || $scope.isSelectedMovie ){
			event.preventDefault();
		}
	});
	
	$document.bind("keydown keypress", function(event) {

		if( event.keyCode === 27 ){ //escape press

			if( $scope.playIsDemand ){
				$scope.closePlayer();
				return;
			}
			
        	if( $scope.isSelectedMovie ){
        		$scope.closePopup();
        		$scope.$apply();
        		return;
        	}
        	
        	if( $scope.search !== '' || $scope.searchClickedClassInput === "searchAreaInput" ){
        		if( $scope.searchClickedClassInput === "searchAreaInput"  ){
        			$scope.toggleClickSearch();
        		}
        		$scope.search = "";
        		$scope.$apply();
        	}
        	
        }
		if ( event.key === "F3" || (event.ctrlKey && event.keyCode === 70 ) ) {  // CTRL-F press or F3
    		$scope.toggleClickSearch();
    		$scope.$apply();
    		event.preventDefault();
    	}
		
    });
	
	$scope.searchDisplay = defaultSearchText;
	$scope.searchClickedClass = 'search-in-mediatheque';
	$scope.searchClickedClassInput = 'searchAreaInputHidden';
	$scope.toggleClickSearch = function(){
		
		if( $scope.searchClickedClass === 'search-in-mediatheque'){
			$scope.searchDisplay = '<img src="images/white-search.png" alt="" alt/>';
			$scope.searchClickedClass = 'search-in-mediatheque click';
			$scope.searchClickedClassInput = 'searchAreaInput';
			setTimeout(function(){angular.element('#searchMovieInputId').focus();},100);
		}
		else{
			$scope.searchDisplay = defaultSearchText;
			$scope.searchClickedClass = 'search-in-mediatheque';
			$scope.searchClickedClassInput = 'searchAreaInputHidden';
		}
	};
	
}]);
