'use strict';

/**
 * @ngdoc service
 * @name mediathequeUiApp.AllocineWebService
 * @description
 * # AllocineWebService
 * Service in the mediathequeUiApp.
 */
angular.module('mediathequeUiApp')
  .service('AllocineWebService', function ($http) {
    
	  var mainURL = 'http://localhost:8080/';
	  var moviesSearch = 'movies/search';
	  
	  this.searchMovie = function ( search , callBack ){
		var response = {};
		if( search === null || search === '' ){
			response = { errorCode : -1 , ErrorDesc: 'Search could not be empty' , movies:[] };
		}else{
			$http.get( mainURL + moviesSearch , {params: {q:search}}).then(
					function( response ){
						response = {
							errorCode:0,
							errorDesc:'',
							movies: response.data
						};
						callBack( response );
					},
					function(){
						response = { errorCode : -2 , ErrorDesc: 'Failed to call WS' , movies:[] };
						callBack( response );
					}
			);
		}
		return response;
	  };
	  
  });
