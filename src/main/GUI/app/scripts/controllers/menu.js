'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp').controller('MenuCtrl', [ 'Security', '$scope' , '$location',
	 function ( Security, $scope , $location) {

	
	
	$scope.$on('$routeChangeSuccess', function () {
        
        var path = $location.path();
        console.log('path : ' + path + ' ' + $scope.page);
        $scope.user = Security.getUser();
        $scope.authenticated = Security.isAuthenticated();
        
        switch( path ){
			case '/':
				$scope.page = 'my-movie';
				break;
			case '/search':
				$scope.page = 'search';
				break;
			case '/parameters' :
			case '/parameters/machine' :
				$scope.page = 'parameters';
				break;
				console.log($scope.page);
			case '/load-disk':
				$scope.page = 'load-disk';
				break;
			case '/csv':
				$scope.page = 'csv';
				break;
			default:
				break;
        }
      
    });
	
	var currentPage = $location.path();
	console.log('current location ' + currentPage);
	
	$scope.page = '';
	
	$scope.logout = function(){
		Security.logout();
	};
	
	//Security managment :
	$scope.isAuthorized = function(page){
		return Security.isAuthorized(page);
	};
	
}]);