'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:UserCtrl
 * @description
 * # UserCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('UserCtrl', ['$scope', '$location' , 'AllocineWebService' , 'localStorageService', 'Security', '$http', 
	  	function ($scope , $location , AllocineWebService , localStorageService, Security , $http) {

	  Security.setHttp( $http );
  
	  $scope.login = function(){
		  console.log( $scope.user );
		  Security.login( $scope.user );
	  };
	  
  }]);
