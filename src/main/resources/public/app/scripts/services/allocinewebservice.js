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
	  var moviesSearch = 'movies/search/';
	  var loadFromDisk = 'movies/my-movies/disk/';
	  var loadMyMovies = 'movies/my-movies/db/';
	  var loadAllParameters = 'parameters/';
	  var addParameters  = 'parameters/add/';
	  var deleteParameters = 'parameters/delete/';
	  var synchronizeMovie = 'movies/my-movies/disk/synchronize';
	  
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
	
	  
	  this.loadAllParameters = function ( callBack ){
		var response = {};
		console.log('Start fetch parameters');
		if( callBack === null || callBack === '' || typeof callBack !== 'function' ){
			console.error( 'Callback function is not defined' );
			return;
		}else{
			$http.get( mainURL + loadAllParameters).then(
					function( response ){
						response = {
							errorCode:0,
							errorDesc:'',
						parameters: response.data
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
	  
	  this.addParameter = function ( callBack , param ){
			var response = {};
			console.log('Start adding parameters');
			if( callBack === null || callBack === '' || typeof callBack !== 'function' ){
				console.error( 'Callback function is not defined' );
				return;
			}else{
				$http.post( mainURL + addParameters, param).then(
						function( response ){
							response = {
								errorCode:0,
								errorDesc:'',
								operation: 'add',
								parameters: response.data
							};
							callBack( response );
						},
						function(){
							response = { errorCode : -2 , errorDesc: 'Impossible de communiquer avec le web service.' , movies:[] };
							response.operation = 'add';
							callBack( response );
						}
				);
			}
	  };
	  
	  this.deleteParameter = function ( callBack , param ){
			var response = {};
			console.log('Start deleting parameters');
			if( callBack === null || callBack === '' || typeof callBack !== 'function' ){
				console.error( 'Callback function is not defined' );
				return;
			}else{
				$http.post( mainURL + deleteParameters, param).then(
						function( response ){
							response = {
								errorCode:0,
								errorDesc:'',
								operation: 'delete',
								parameters: response.data
							};
							callBack( response );
						},
						function(){
							response = { errorCode : -2 , errorDesc: 'Impossible de communiquer avec le web service.' , movies:[] };
							response.operation = 'delete';
							callBack( response );
						}
				);
			}
	  };
	  
	  this.synchronizeMovie = function( callback, localMovie, code){
		  
		  if( localMovie !== undefined && code !== undefined ){
			  var movieData = angular.toJson(localMovie, false);
			  $http.post( mainURL + synchronizeMovie ,  movieData ).then(
					  
						function( response ){
							response = {
								errorCode:0,
								errorDesc:'',
							parameters: response.data
						};
						callback( response );
					},
					
					function(){
						var response = { errorCode : -3 , errorDesc: 'Impossible de communiquer avec le web service.' , movies:[] };
						callback( response );
					}
				);
			  
		  }
	  };
	  
	  this.loadMyMovie = function ( callBack ){
			var response = {};
			if( callBack === null || callBack === '' || typeof callBack !== 'function' ){
				console.error( 'Callback function is not defined' );
				return;
			}else{
				$http.get( mainURL + loadMyMovies ).then(
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

