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
    'ngFileUpload'
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
      .otherwise({
        redirectTo: '/'
      });
  });
