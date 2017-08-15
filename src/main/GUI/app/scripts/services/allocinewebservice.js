'use strict';

/**
 * @ngdoc service
 * @name mediathequeUiApp.AllocineWebService
 * @description
 * # AllocineWebService
 * Service in the mediathequeUiApp.
 */
angular.module('mediathequeUiApp')
  .service('AllocineWebService', function ($http, Upload) {
    
	  var serviceIsReady = true;
	  
	  console.log("get properties before");
	  
	  var host = undefined;
	  var port = undefined;
	  var mainURL = 'http://'+host+':'+port+'/';
	  var moviesSearch = 'movies/search/';
	  var loadFromDisk = 'movies/my-movies/disk/';
	  var loadMyMovies = 'movies/my-movies/db/';
	  var loadAllParameters = 'parameters/';
	  var addParameters  = 'parameters/add/';
	  var deleteParameters = 'parameters/delete/';
	  var synchronizeMovie = 'movies/my-movies/disk/synchronize';
	  var listMachine = 'machine/list';
	  var addMachine = 'machine/add';
	  var listOnMachine = 'movies/my-movie/machine/search';
	  var searchSeries = 'series/search';
	  var serieDetails = 'series/details';

	  this.executeQueryAfterInit = function( callback ){
		  
		  if( angular.isUndefined(host)
				  || angular.isUndefined(port) ){
			  console.log("get properties");
			  $http.get('config/webservice.properties').then(function(response){
				  console.log(response.data);
				  if( angular.isDefined(response.data) 
						  && angular.isDefined(response.data.host) ){
					  host = response.data.host;
				  }
				  if( angular.isDefined(response.data) 
						  && angular.isDefined(response.data.port) ){
					  port = response.data.port;
				  }
				  serviceIsReady = true;
				  mainURL = 'http://'+host+':'+port+'/';

				  callback();
				  
			  });
		  }else{
			  callback();
		  }
		  
	  }
	  
	  this.searchMovie = function ( search , callBack ){
		  this.executeQueryAfterInit( function(){
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
		  });
	};
	  
	  
	  
	  this.loadDisk = function ( search , callBack ){
		  this.executeQueryAfterInit( function(){
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
		  });};
	
	  
	  this.loadAllParameters = function ( callBack ){
		  this.executeQueryAfterInit( function(){
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
		  });
		};
	  
	  this.addParameter = function ( callBack , param ){
		  this.executeQueryAfterInit( function(){
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
		  });
	  };
	  
	  this.deleteParameter = function ( callBack , param ){
		  this.executeQueryAfterInit( function(){
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
		  }); };
	  
	  this.synchronizeMovie = function( callback, localMovie, code){
		  this.executeQueryAfterInit( function(){
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
		  });
		 };
	  
	  this.loadMyMovie = function ( callBack ){
		  this.executeQueryAfterInit( function(){
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
		  });};
	  
	this.uploadCSVMovies = function( file , callback ){
		this.executeQueryAfterInit( function(){
		  console.log( file );
		  Upload.upload({
	            url: mainURL + 'movies/csv',
	            data: {file: file, name : file.name}
	        }).then(function (response) {
	            callback( response );
	        }, function (response) {
	            callback( response );
	        }, function (evt) {
	            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
	            console.log(evt);
	        });
		});};	  
		
		
		
		this.addMachine = function( params, callback ){
			this.executeQueryAfterInit( function(){
			  $http.post( mainURL + addMachine , params).then(
						function( response ){
							response = {
								errorCode:0,
								errorDesc:'',
								success: response.data
							};
							callback( response );
						},
						function(){
							response = { errorCode : -2 , errorDesc: 'Impossible de communiquer avec le web service.' , movies:[] };
							callBack( response );
						}
				);
			});
		};	 
		
		this.listAllMachines = function( callback ){
			this.executeQueryAfterInit( function(){
			  $http.post( mainURL + listMachine ).then(
						function( response ){
							response = {
								errorCode:0,
								errorDesc:'',
								data: response.data
							};
							callback( response );
						},
						function(){
							response = { errorCode : -2 , errorDesc: 'Impossible de communiquer avec le web service.' , movies:[] };
							callBack( response );
						}
				);
			});
		};	  
		  
		
		this.listMoviesOnMachines = function( machine, path , callback ){
			this.executeQueryAfterInit( function(){
				
				machine.path = path;
				
				$http.post( mainURL + listOnMachine , machine ).then(
						function( response ){
							response = {
								errorCode:0,
								errorDesc:'',
								movies: response.data
							};
							callback( response );
						}, function(){
							var response = { errorCode : -2 , errorDesc: 'Impossible de communiquer avec le web service.' , movies:[] };
							callback( response );
						});
			} );
		};
		
		this.searchSerie = function( search , callback ){
			this.executeQueryAfterInit( function(){

				$http.get( mainURL + searchSeries ,  {params: {q:search}} ).then(
					function(response){
						callback( response.data );
					},
					function( response ){
						callback(response);
					}
				);

			});
		};

		this.serieDetails = function( serie , callback ){
			this.executeQueryAfterInit( function(){

				$http.post( mainURL + serieDetails , serie ).then(
					function(response){
						callback( response.data );
					},
					function( response ){
						callback( response );
					}
				);

			});
		};

  });

function sleep(milliseconds) {
//	  var start = new Date().getTime();
//	  for (var i = 0; i < 1e7; i++) {
//	    if ((new Date().getTime() - start) > milliseconds){
//	      break;
//	    }
//	  }
	}