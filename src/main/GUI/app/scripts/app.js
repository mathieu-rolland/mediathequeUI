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
    'ui.bootstrap'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/my-movies',{
    	  templateUrl: 'views/mymovies.html',
          controller: 'MymoviesCtrl',
          controllerAs: 'mymovies'
      })
    .when('/load-disk',{
    	  templateUrl: 'views/loadfromdisk.html',
          controller: 'LoadfromdiskCtrl',
          controllerAs: 'loadfromdisk'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
