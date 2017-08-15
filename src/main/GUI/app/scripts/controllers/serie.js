'use strict';

/**
 * @ngdoc function
 * @name mediathequeUiApp.controller:SerieCtrl
 * @description
 * # SerieCtrl
 * Controller of the mediathequeUiApp
 */
angular.module('mediathequeUiApp')
  .controller('SerieCtrl', ['AllocineWebService',function ( AllocineWebService ) {
    
    var serieDetailsResponseCallback = function( response ){
      console.log( response );
    };

    var serieSearchResponseCallback = function( response ){
      console.log(response);

      var code = response[0].code;
      AllocineWebService.serieDetails( {code:code} , serieDetailsResponseCallback );

    }
    
  AllocineWebService.searchSerie( "Game of throne" , serieSearchResponseCallback );

  }]);
