'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:UserCtrl
 * @description
 * # UserCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('UserCtrl', function ($scope , $location , AllocineWebService , localStorageService, Security) {

	  var currentToken = "";//localStorageService.get('auth-token');
	  
	  if( angular.isDefined(currentToken) && currentToken != "" ){
		  //AllocineWebService.setToken( currentToken );
		  //$location.path('/');
		  console.log( currentToken );
	  }
	  
	  var loginCallback = function( response ){
//		  AllocineWebService.setToken( response.data.token );
//		  console.log( response.data.token );
//		  localStorageService.set ('auth-token' , response.data.token );
//		  $location.path('/');
	  };
	  
	  $scope.login = function(){
		  console.log( $scope.user );
		  //AllocineWebService.login( $scope.user , loginCallback );
		  Security.login();
	  };
	  
  });
