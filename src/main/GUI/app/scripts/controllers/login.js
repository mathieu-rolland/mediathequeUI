'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:UserCtrl
 * @description
 * # UserCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('LoginCtrl', ['$scope', '$location' , 'AllocineWebService' , 'localStorageService', 'Security', '$http', '$routeParams', '$timeout',
	  	function ($scope , $location , AllocineWebService , localStorageService, Security , $http, $routeParams , $timeout) {

	  Security.setHttp( $http );
	  
	  $scope.alerts = [];
	  
	  $scope.login = function(){
		  console.log( $scope.user );
		  Security.login( $scope.user , securityCallback );
	  };
	  
	  $scope.create = function(){
		  console.log( $scope.user );
		  Security.checkUser( $scope.user , securityCheckCallback);
	  }
	  
	  var securityCheckCallback = function(response , message){
		  console.log("Check user : " + response.data );
		  //Security.create( $scope.user , securityCallback );
		  if( response.data !== 0){
		   	$scope.alerts.push( { type : "danger" , msg: message} );
			  $timeout( function(){
				  $scope.closeAlert( $scope.alerts.length -1 );
			  } , 2000 );
		  }else{
			  Security.create( $scope.user , securityCallback );
		  }
	  };
	  
	  //user activation :
	  var currentLocation = $location.path();
	  if( currentLocation === '/user/active-account' ){
		  console.log("Key : " + $routeParams.q );
		  Security.activeAccount( $routeParams.q );
	  }
	  
	  var securityCallback = function( success , redirection , message ){
		  if( success ){
			  $location.path( redirection );
		  }else{
			  $scope.alerts.push( { type : "danger" , msg: message} );
			  $timeout( function(){
				  $scope.closeAlert( $scope.alerts.length -1 );
			  } , 2000 );
		  }
	  };

	  $scope.closeAlert = function(index){
		  $scope.alerts.splice(index , 1);
	  };
	  
	  $scope.askingRegister = function(){
		  angular.element('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	  };
	  
  }]);
