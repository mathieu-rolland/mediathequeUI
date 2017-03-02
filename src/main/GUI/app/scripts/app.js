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
  .config(function ($routeProvider , localStorageServiceProvider) {
	
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
      .when('/user',{
    	  templateUrl : 'views/user.html',
    	  controller: 'UserCtrl',
    	  controllerAs: 'userctrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
