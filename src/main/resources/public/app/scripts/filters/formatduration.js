'use strict';

/**
 * @ngdoc filter
 * @name mediathequeUiApp.filter:formatDuration
 * @function
 * @description
 * # formatDuration
 * Filter in the mediathequeUiApp.
 */
angular.module('mediathequeUiApp')
  .filter('formatDuration', function () {
    return function (input) {
    	var hours = parseInt(input/3600);
    	var mins = parseInt((input - (hours*3600))/60);
      return hours + 'h' + (mins < 10 ? '0' + mins : mins);
    };
  });
