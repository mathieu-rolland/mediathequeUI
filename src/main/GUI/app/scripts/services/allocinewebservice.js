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
    
	  var mainURL = 'http://localhost:8989/';
	  var moviesSearch = 'movies/search';
	  var loadFromDisk = 'movies/my-movies/disk';
	  
	  this.searchMovie = function ( search , callBack ){
		var response = {};
		console.log(search);
		if( search === null || search === '' || search === undefined ){
			response = { errorCode : -1 , errorDesc: 'La recherche ne peut pas être vide.' , movies:[] };
			callBack( response );
			return;
		}
		else if( callBack === null || callBack === '' || typeof callBack !== 'function' ){
			console.error( 'Callback function is not defined' );
			return;
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
						response = { errorCode : -2 , errorDesc: 'Impossible de communiquer avec le web service.' , movies:[] };
						callBack( response );
					}
			);
		}
	  };
	  
	  
	  
	  this.loadDisk = function ( search , callBack ){
		var response = {};
		console.log(search);
		if( search === null || search === '' || search === undefined ){
			response = { errorCode : -1 , errorDesc: 'La recherche ne peut pas être vide.' , movies:[] };
			callBack( response );
			return;
		}
		else if( callBack === null || callBack === '' || typeof callBack !== 'function' ){
			console.error( 'Callback function is not defined' );
			return;
		}else{
			$http.get( mainURL + loadFromDisk , {params: {q:search}}).then(
					function( response ){
						response = {
							errorCode:0,
							errorDesc:'',
							movies: response.data
						};
						callBack( response );
					},
					function(){
						response = { errorCode : -2 , errorDesc: 'Impossible de communiquer avec le web service.' , movies:[] };
						callBack( response );
					}
			);
		}
	  };
	  
  });
