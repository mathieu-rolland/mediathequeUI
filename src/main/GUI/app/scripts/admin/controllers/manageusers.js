'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('ManageUserCtrl', [ '$scope', 'Security', '$http' ,function ($scope, Security, $http) {

	  Security.setHttp( $http );
	  $scope.pageLoaded = false;
	  
	  var userListCallback = function( httpResponse ){
		  
		  if( angular.isDefined(httpResponse) && angular.isDefined(httpResponse.data) ){
			  $scope.userList = httpResponse.data;
		  }
		  
		  $scope.pageLoaded = true;
		  
	  };
	  
	  Security.getAllUsers( userListCallback );
	  
  }]);
