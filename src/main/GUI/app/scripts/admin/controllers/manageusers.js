'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('ManageUserCtrl', [ '$scope', 'Security', '$http' , '$timeout' ,
  	function ($scope, Security, $http, $timeout) {

	  Security.setHttp( $http );
	  $scope.pageLoaded = false;
	  
	  $scope.alerts = [];

	  var userListCallback = function( httpResponse ){
		  
		  if( angular.isDefined(httpResponse) && angular.isDefined(httpResponse.data) ){
			  $scope.userList = httpResponse.data;
		  }
		  
		  $scope.pageLoaded = true;
		  
	  };
	  
	  Security.getAllUsers( userListCallback );
	  
	  var userBanCallback = function(index , response){
			if( response.status !== 200 ){
				console.log( response );
				$scope.alerts.push( { type : 'danger' , msg : 'Erreur survenue dans le web service (' + response.status +')' } );
			}else{
				if( response.data !== "" ){
					var user = response.data;
					var message = "";
					
					//display alert : 
					if( user.suspended ) message = 'Utilisateur banni avec succès';
					else message = 'Utilisateur réactivé avec succès';
					$scope.alerts.push( { type : 'success' , msg : message } );
					$timeout(function(){
						$scope.alerts.splice( $scope.alerts.length -1 );
					} , 2000 );

					//update model value : 
					/*$scope.userList.forEach(function(element) {
						if( element.name === user.name ){
							element.suspended = user.suspended;
						}
					}, this);*/
					$scope.userList[index].suspended = user.suspended;

				}
			}
	  };

	  $scope.bannir = function( index , user ){
		  Security.banUser( index , user , userBanCallback );
	  };
	  
	  $scope.closeAlert = function( index ){
		$scope.alerts.splice( index , 1 );
	  };

  }]);
