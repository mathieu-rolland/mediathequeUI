'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:ParametersCtrl
 * @description
 * # ParametersCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp').controller('ParametersCtrl', [ '$scope', 'AllocineWebService', function ( $scope , AllocineWebService) {

	$scope.addParameter = function( newParameter ){
		
		console.log('add parameter : ' + newParameter.name + '/' + newParameter.value );
		AllocineWebService.addParameter( responseCallback , newParameter );
		
	};
	
	$scope.deleteParameter = function( parmaterToDelete ){
		
		console.log('add parameter : ' + parmaterToDelete.name + '/' + parmaterToDelete.value );
		AllocineWebService.deleteParameter( responseCallback , parmaterToDelete );
		
	};
	
	var responseCallback = function( data ){
		
		console.log( 'Reponse received : ');
		console.log( data  );
		
		if( data.errorCode !== 0 ){
			console.log('An error occured');
			$scope.addAlert( data.errorDesc , 'danger' );
			return;
		}
		
		if( data.parameters !==  null && data.parameters !== '' && data.parameters.length !== 0 ){
			$scope.parameters = data.parameters;
		}
		
		if( data.operation === 'add' ){
			$scope.newParameter = {
				name: '',
				value: ''
			};
		}
		
	};
	
	$scope.addAlert = function(message, errorType) {
		console.log(message + ' => ' + errorType );
	    alerts.push({msg: message, type:errorType});
	};
	
	$scope.closeAlert = function(index) {
	    $scope.alerts.splice(index, 1);
	};
	
	//start module: 
	var alerts = $scope.alerts = [];
	
	//When page loaded, load parameters :
	AllocineWebService.loadAllParameters( responseCallback );

}]);
