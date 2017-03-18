'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:UserCtrl
 * @description
 * # UserCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('UserCtrl', ['$scope', '$location' , 'AllocineWebService' , 'localStorageService', 'Security', '$http', '$routeParams',
	  	function ($scope , $location , AllocineWebService , localStorageService, Security , $http, $routeParams) {

	  Security.setHttp( $http );
  
	  $scope.login = function(){
		  console.log( $scope.user );
		  Security.login( $scope.user );
	  };
	  
	  $scope.create = function(){
		  console.log( $scope.user );
		  Security.create( $scope.user );
	  }
	  
	  //user activation :
	  var currentLocation = $location.path();
	  if( currentLocation === '/user/active-account' ){
		  console.log("Key : " + $routeParams.q );
		  Security.activeAccount( $routeParams.q );
	  }
	  
	  $scope.askingRegister = function(){
		  angular.element('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	  };
	  
  }]);
