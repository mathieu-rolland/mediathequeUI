'use strict';

/**
 * @ngdoc service
 * @name mediathequeUiApp.security
 * @description
 * # security
 * Factory in the mediathequeUiApp.
 */
angular.module('mediathequeUiApp')
  .service('Security', [ '$location', function ( $location ) {

	  var service = this;
	  
	  var token = '';
	  var userLevel; // to be loaded
	  
	  var $http;
	  
	  var  host, port;
	  var mainURL = undefined;
	  var loginUser = 'user/login';
	  
	  service.setHttp = function( http ){
		  service.http = http;
	  };
	  
	  function preconfigure( callback ){
		  
		  if (angular.isUndefined(service.mainUrl)){
			  
			  service.http.get('config/webservice.properties').then(function(response){
				  if( angular.isDefined(response.data) 
						  && angular.isDefined(response.data.host) ){
					  host = response.data.host;
				  }
				  if( angular.isDefined(response.data) 
						  && angular.isDefined(response.data.port) ){
					  port = response.data.port;
				  }
				  service.mainURL = 'http://'+host+':'+port+'/';
				  callback();
			  });
		  }else{
			  callback();
		  }
	  };
	  
	  service.login = function( user ){
		  
		  console.log('login in progress...');
		  return preconfigure (
				function(){
					console.log(service.mainURL + 'user/login');
				  service.http.post( service.mainURL + 'user/login' , user )
			  		.then(function (response) {
		              if (response.data && response.data.token) {
		            	  service.token=response.data.token;
		                  console.log( response.data.token );
		                  $location.path('/');
		              }
	          });
		  });
	  };
	  
	  service.logout = function(){
		  service.token = '';
		  $location.path('/user');
	  };
	  
	  service.getToken = function(){
		  return service.token;
	  };
	  
  }])
  .service('httpSecurityInterceptor', [ '$location', 'Security', function( $location, Security ){
	  
	  var service = this;
	  
	  service.request = function(config){
		  console.log("Use token : " + Security.getToken() );
			config.headers["X-Access-Token"] = Security.getToken() ;
			return config;
	  };
	  
	  service.responseError = function( response ){
		  console.log(response);
		  if( angular.isDefined(response) && angular.isDefined(response.status)){
			  
			  if( response.status == 401 ){
				  $location.path('/user');
			  }
			  
			  if( response.status == 403 ){
				  console.log("Not allowed to acces to " + $location.path() );
			  }
			  
		  }
		  
		  return response;
		  
	  }
	  
  }])
  .config([ '$httpProvider', function($httpProvider) {
           $httpProvider.interceptors.push('httpSecurityInterceptor');
  }])
;
