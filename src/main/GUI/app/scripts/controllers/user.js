'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:UserCtrl
 * @description
 * # UserCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('UserCtrl', function ($scope , AllocineWebService) {

	  var loginCallback = function( data ){
		  console.log( data );
	  };
	  
	  $scope.login = function(){
		  console.log( $scope.user );
		  AllocineWebService.login( $scope.user , loginCallback );
	  };
	  
  });
