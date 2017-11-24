'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:SeriemanagmentCtrl
 * @description
 * # SeriemanagmentCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('SeriemanagmentCtrl', [ 'AllocineWebService' ,  '$scope' ,
  function ( AllocineWebService , $scope ) {

    /*Controller environnement variables*/
    $scope.series = {
      ariane:[]
    };

    $scope.showSerieSearchResults = true;
    $scope.showSerieDetailshResults = false;

    var updateAriane = function( action , element ){
      if( action === 'DELETE' ){
        $scope.series.ariane.slice( $scope.series.ariane.length );
      }else{
        $scope.series.ariane.push( element );
      }
    };

    /*Web service callback : */
    var searchSerieCallback = function( response ){
        console.log(response);
        $scope.series.search.result = response;
        $scope.showSerieSearchResults = true;
        $scope.showSerieDetailshResults = false;
    };

    var serieDetailsCallback = function(response){

      updateAriane( 'ADD' , response.title );
      $scope.series.seasons = Array();

      if( angular.isDefined(response) ){
        
        $scope.series.seasons = response.seasons;
        
      }
      $scope.showSerieSearchResults = false;
      $scope.showSerieDetailshResults = true;
    };

    var getChaptersCallback = function( response ){
      if( angular.isDefined(response) ){
          console.log( "Serie chapter : " + response);
      }
    };

    /*Web service requests : */
    $scope.searchSerie = function( query ){  
      AllocineWebService.searchSerie( query , searchSerieCallback );
    };

    $scope.getSerieDetails = function( serie ){
      AllocineWebService.serieDetails( serie , serieDetailsCallback );
    };
    
    $scope.getChapters = function( season ){
      console.log(season);
      for( var i = 0 ; i < season.chapters.length ; i++ ){
        AllocineWebService.chapterDetails( season.chapters[i] , getChaptersCallback );
      }
    };

  }]);