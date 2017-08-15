'use strict';

/**
 * @ngdoc overview
 * @name mediathequeUiApp
 * @description
 * # mediathequeUiApp
 *
 * Main module of the application.
 */
angular
  .module('mediathequeUiApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap',
    'LocalStorageModule',
    'ngFileUpload',
	'com.2fdevs.videogular',
	'com.2fdevs.videogular.plugins.controls',
	'com.2fdevs.videogular.plugins.overlayplay',
	'com.2fdevs.videogular.plugins.poster',
	'com.2fdevs.videogular.plugins.buffering'
  ])
  .config([ '$routeProvider' , 'localStorageServiceProvider' , '$httpProvider', function ($routeProvider , localStorageServiceProvider, $httpProvider) {
	
	localStorageServiceProvider.setPrefix('uiMediatheque');
	  
    $routeProvider
      .when('/search', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/',{
    	  templateUrl: 'views/mymovies.html',
          controller: 'MymoviesCtrl',
          controllerAs: 'mymovies'
      })
    .when('/load-disk',{
    	  templateUrl: 'views/loadfromdisk.html',
          controller: 'LoadfromdiskCtrl',
          controllerAs: 'loadfromdisk'
      })
      .when('/parameters',{
    	  templateUrl: 'views/parameters.html',
          controller: 'ParametersCtrl',
          controllerAs: 'parameters'
      })
      .when('/parameters/machine',{
    	  templateUrl: 'views/machineparameter.html',
          controller: 'MachineparameterCtrl',
          controllerAs: 'machineParameters'
      })
      .when('/load-disk/search',{
    	  templateUrl: 'views/searchlocalmovietoallocine.html',
          controller: 'SearchlocalmovietoallocineCtrl',
          controllerAs: 'Searchlocalmovietoallocine'
      })
      .when('/csv',{
    	  templateUrl : 'views/csvupload.html',
    	  controller: 'CsvuploadCtrl',
    	  controllerAs: 'cvupload'
      })
      .when('/login',{
    	  templateUrl : 'views/login.html',
    	  controller: 'LoginCtrl',
    	  controllerAs: 'LoginCtrl'
      })
      .when('/admin/users',{
    	  templateUrl : 'views/admin/manageusers.html',
    	  controller: 'ManageUserCtrl',
    	  controllerAs: 'ManageUserCtrl'
      })
      .when('/user/active-account',{
    	  templateUrl : 'views/login.html',
    	  controller: 'LoginCtrl',
    	  controllerAs: 'LoginCtrl'
      })
      .when('/serie', {
        templateUrl: 'views/serie.html',
        controller: 'SerieCtrl',
        controllerAs: 'serie'
      })
      .otherwise({
        redirectTo: '/'
      });
    
    /*$httpProvider.interceptors.push(function(){
    	return {
    		'request' : function(config){
    			config.headers["X-Access-Token"] = 'my custom token';
    			return config;
    		}
    	};
    })*/
    
  }]);
