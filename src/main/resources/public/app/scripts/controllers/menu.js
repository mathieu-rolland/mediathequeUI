'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp').controller('MenuCtrl', function ( $scope , $location) {


	$scope.$on('$routeChangeSuccess', function () {
        
        var path = $location.path();
        console.log('path : ' + path + ' ' + $scope.page);
        
        switch( path ){
			case '/':
				$scope.page = 'my-movie';
				break;
			case '/search':
				$scope.page = 'search';
				break;
			case '/parameters' :
				$scope.page = 'parameters';
				break;
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
	
});