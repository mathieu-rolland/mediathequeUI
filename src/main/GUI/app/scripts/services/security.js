'use strict';

/**
 * @ngdoc service
 * @name mediathequeUiApp.security
 * @description
 * # security
 * Factory in the mediathequeUiApp.
 */
angular.module('mediathequeUiApp')
  .service('Security', [ '$http' , 'AllocineWebService', function ($http , AllocineWebService) {

	  var service = this;
	  
	  var token;
	  var userLevel; // to be loaded
	  
	  var  host, port;
	  var mainURL = "";
	  var loginUser = 'user/login';
	  
	  service.login = function( username, password ){

		  mainURL = AllocineWebService.getmainUrl();
		  
		  $http.get('config/webservice.properties').then(function(response){
			  console.log(response.data);
			  if( angular.isDefined(response.data) 
					  && angular.isDefined(response.data.host) ){
				  host = response.data.host;
			  }
			  if( angular.isDefined(response.data) 
					  && angular.isDefined(response.data.port) ){
				  port = response.data.port;
			  }
			  mainURL = 'http://'+host+':'+port+'/';
			  console.log("Main url is " + mainURL );
			  console.log('login in progress...');
			  return $http.post( mainURL + loginUser , {name: username, password: password})
	          .then(function (response) {
	              if (response.data.token) {
	                  token=response.data.token;
	              }
	          });
		  });
		  
	  };
	 
	  service.getToken = function(){
		  return toekn;
	  };
	  
	  
	
  }])
  .service('httpSecurityInterceptor', [ '$location' , function( $location ){
	  
	  var service = this;
	  
	  service.request = function(config){
			config.headers["X-Access-Token"] = 'my custom token';
			return config;
	  };
	  
	  service.responseError = function( response ){
		  console.log(response);
		  if( angular.isDefined(response) && angular.isDefined(response.status)){
			  
			  if( response.status == 401 ){
				  $location.path('/user');
			  }
		  }
		  
		  return response;
		  
	  }
	  
  }])
  .config([ '$httpProvider', function($httpProvider) {
           $httpProvider.interceptors.push('httpSecurityInterceptor');
  }])
;


//.config(
//		['$httpProvider',function ($httpProvider) {
////
//        $httpProvider.interceptors.push(["$rootScope",function ($rootScope) {
//            return {     //intercept only the response
//                        'response': function (response) 
//                                    {
//                                      console.log(response);
//                                      return response;
//                                    }
//                   };
//        }])
//}]
//);