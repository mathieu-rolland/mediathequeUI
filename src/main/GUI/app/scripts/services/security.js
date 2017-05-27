'use strict';

/**
 * @ngdoc service
 * @name mediathequeUiApp.security
 * @description
 * # security
 * Factory in the mediathequeUiApp.
 */
angular.module('mediathequeUiApp')
  .service('Security', [ '$location' , 'localStorageService' , function ( $location , localStorageService ) {

	  var service = this;
	  
	  var token = '';
	  var userLevel; // to be loaded
	  
	  var $http;
	  
	  var  host, port;
	  var mainURL = undefined;
	  var loginUser = 'user/login';
	  
	  var currentUser;
	  
	  var userPages = {};
	  userPages['my-movies'] = true;
	  
	  service.setHttp = function( http ){
		  service.http = http;
	  };
	  
	  //Init : 
	  var tokenInStorage = localStorageService.get('auth-token');
	  if( angular.isDefined(tokenInStorage) && tokenInStorage !== "" ){
		  
		  service.token = tokenInStorage;
		  service.currentUser = localStorageService.get('auth-user');
		  $location.path('/');
		  
	  }
	  //End of init
	  
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
	  
	  service.login = function( user , callback ){
		  
		  console.log('login in progress...');
		  
		  return preconfigure (
				function(){
				  service.http.post( service.mainURL + 'user/login' , user )
			  		.then(function (response) {
		              if (	response.data && angular.isDefined( response.data.token ) ) {
		            	  console.log(response.data.user);
		            	  if( !response.data.user.activated ){
		            		  callback( false , "" , "Veuillez activer votre compte." );
		            	  }else{
			            	  service.token=response.data.token;
			            	  service.currentUser=response.data.user;
			            	  localStorageService.set('auth-token' , service.token );
			            	  localStorageService.set('auth-user' , service.currentUser );
			                  callback( true , "/" , "" );
		            	  }
		              }else{
		            	  callback( false , "" , "Utilisateur ou mot de passe incorrecte" );
		              }
	          });
		  });
	  };
	  
	  service.create = function( user ){
		  
		  preconfigure(function(){
			  
			  service.http.post( service.mainURL + 'user/create-account' , user )
		  		.then(function (response) {
		  				console.log( response );
		  				if( response && response.status && response.status === 200 ){
		  					var userCreated = response.data;		  					
		  					if( userCreated.name === user.name
		  						&& userCreated.email === user.email ){
		  						$location.path('/');
		  						callback( true, '/' );
		  					}else{
		  						callback( false , '' , "Erreur lors de la création du compte" );
		  					}
		  				}else{
		  					callback( false , '' , "Erreur lors de la création du compte" );
		  				}
		  		});
		  		
		  });
		  
	  };
	  
	  service.activeAccount = function( key ){
		  
		  preconfigure(function(){
			  service.http.get( service.mainURL + 'user/active-account?q=' +  key )
		  		.then(function (response) {
		  			console.log(response);
		  		});
		  });
		  
	  }
	  
	  service.logout = function(){
		  service.token = undefined;
		  service.currentUser = undefined;
		  localStorageService.set('auth-token' , undefined);
		  $location.path('/login');
	  };
	  
	  service.getToken = function(){
		  return service.token;
	  };
	  
	  service.getUser = function(){
		  return service.currentUser;
	  };
	  
	  service.isAuthenticated = function(){
		return angular.isDefined(service.token) && angular.isDefined(service.currentUser);  
	  };
	  
	  service.isAuthorized = function(page){

		  if( service.isAuthenticated() ){
			  
			  var roles = service.currentUser.roles;
	
			  if( angular.isDefined(roles)){
				  
				  var isAuthorized = false;
		
				  roles.forEach( function(el){
					  if( el === 'USER' && userPages[page] ){
						  //console.log( 'User is authorized for page ' + page );
						  isAuthorized = true;
					  }
					  else if( el === 'ADMIN' ){
						  //console.log( 'Admin is authorized for page ' + page );
						  isAuthorized = true;
					  }
				  });
				  return isAuthorized;
				  
			  }else{
				  return false;
			  }
		  }
		  console.log( 'Not authorized for page ' + page );
		  return false;
	  }
	  
	  service.getAllUsers = function( callback ){
		  preconfigure( function(){
			
			  service.http.get( service.mainURL + 'user/list-users' )
		  		.then(function (response) {
		  			callback(response);
		  		});
			  
		  });
	  };
	  
	  
	  service.checkUser = function( user , callback){
		  preconfigure(function(){
			  
			  service.http.post( service.mainURL + 'user/check-user' , user )
		  		.then(function (response) {
		  			var returnCode = response.data;
		  			var message = "";
		  			if( returnCode === 100 ){
		  				message = "L'utilisateur existe déjà";
		  			}else if( returnCode === 200 ){
		  				message = "L'email n'est pas correctement rempli";
		  			}else if( returnCode === 201 ){
		  				message = "L'email n'est pas valide";
		  			}else if( returnCode === 300 ){
		  				message = "Le nom d'utilisateur n'est pas valide";
		  			}else if( returnCode === 301 ){
		  				message = "Ce nom d'utilisateur existe déjà";
		  			}else if( returnCode === 300 ){
		  				message = "Le mot de passe n'est pas valide";
		  			}
		  			callback(response , message);
		  		});
		  		
		  });
	  }
	  
  }])
  .service('httpSecurityInterceptor', [ '$location', 'Security', function( $location, Security ){
	  
	  var service = this;
	  
	  service.request = function(config){
		  	console.log("Use token : " + Security.getToken() );
			config.headers["X-Access-Token"] = Security.getToken() ;
			return config;
	  };
	  
	  service.responseError = function( response ){
		  if( angular.isDefined(response) && angular.isDefined(response.status)){
			  
			  if( response.status == 401 ){
				  Security.logout(); // used to clean in memory token
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
