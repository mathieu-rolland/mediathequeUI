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
			$scope.addAlert( response.errorDesc , 'danger' );
			return;
		}
		
		for(var i = 0 ; i < response.movies.length ; i++){
			var poster = response.movies[i].poster;
			console.log(poster);
			if( poster.href === undefined){
				poster.href = 'images/yeoman.png';
			}
			$scope.addSlide(poster.href , response.movies[i].title );
		}
		 
		$scope.movies.list = response.movies;
		
		
	};
	
	$scope.searchMovie = function(search){
		console.log('search : ' + search );
		console.log( AllocineWebService.searchMovie( search , responseCallBack) );
	};
	
	$scope.myInterval = 15000;
	  $scope.noWrapSlides = false;
	  $scope.active = 0;
	  var slides = $scope.slides = [];
	  var currIndex = 0;

	  $scope.addSlide = function( link, title ) {
//	    var newWidth = 600 + slides.length + 1;
//	    slides.push({
//	      image: 'http://lorempixel.com/' + newWidth + '/300',
//	      text: ['Nice image','Awesome photograph','That is so cool','I love that'][slides.length % 4],
//	      id: currIndex++
//	    });
		  slides.push({
		      image: link,
		      text: title,
		      id: currIndex++
		    });
	  };

	  $scope.randomize = function() {
	    var indexes = generateIndexesArray();
	    assignNewIndexesToSlides(indexes);
	  };

	  for (var i = 0; i < 4; i++) {
//	    $scope.addSlide();
	  }

	  // Randomize logic below

	  function assignNewIndexesToSlides(indexes) {
	    for (var i = 0, l = slides.length; i < l; i++) {
	      slides[i].id = indexes.pop();
	    }
	  }

	  function generateIndexesArray() {
	    var indexes = [];
	    for (var i = 0; i < currIndex; ++i) {
	      indexes[i] = i;
	    }
	    return shuffle(indexes);
	  }

	  // http://stackoverflow.com/questions/962802#962890
	  function shuffle(array) {
	    var tmp, current, top = array.length;

	    if (top) {
	      while (--top) {
	        current = Math.floor(Math.random() * (top + 1));
	        tmp = array[current];
	        array[current] = array[top];
	        array[top] = tmp;
	      }
	    }

	    return array;
	  }
	
	  var alerts = $scope.alerts = [];
	  
	  $scope.addAlert = function(message, errorType) {
		console.log(message + ' => ' + errorType );
	    alerts.push({msg: message, type:errorType});
	  };
	  
	  $scope.closeAlert = function(index) {
	    $scope.alerts.splice(index, 1);
	  };
	  
	  $scope.rate = 7;
	  $scope.max = 5;
	  $scope.isReadonly = false;

	  $scope.hoveringOver = function(value) {
	    $scope.overStar = value;
	    $scope.percent = 100 * (value / $scope.max);
	  };

	  $scope.ratingStates = [
	    {stateOn: 'glyphicon-ok-sign', stateOff: 'glyphicon-ok-circle'},
	    {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
	    {stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle'},
	    {stateOn: 'glyphicon-heart'},
	    {stateOff: 'glyphicon-off'}
	  ];
	  
}]);
