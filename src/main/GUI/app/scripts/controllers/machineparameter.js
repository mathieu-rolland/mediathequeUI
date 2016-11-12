'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:MachineparameterCtrl
 * @description
 * # MachineparameterCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('MachineparameterCtrl', function ($scope, AllocineWebService) {

	  //Variables communes :
	  $scope.machines = [];
	  
	  //Fonction callback :
	  var addMachineCallback = function(response){
		  if( response.errorCode == 0 ){
			  if( response.success ){
				  AllocineWebService.listAllMachines( listAllMachinesCallback );
			  }
		  }
	  }
	  
	  var listAllMachinesCallback = function( response ){
		  if( response != null ){
			  
			  if( response.errorCode  == 0 ){
				  if( angular.isDefined(response.data) ){
					  $scope.machines = response.data;
				  }
			  }
			  
		  }
	  }
	  
	  AllocineWebService.listAllMachines( listAllMachinesCallback );
	  
	  //Fonction FO :
	  $scope.addMachine = function(newMachine){
		  console.log(newMachine);
		  AllocineWebService.addMachine( newMachine , addMachineCallback );
	  }
	  
	  
	  
  });
