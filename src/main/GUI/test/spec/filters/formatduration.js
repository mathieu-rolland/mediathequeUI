'use strict';

describe('Filter: formatDuration', function () {

  // load the filter's module
  beforeEach(module('mediathequeUiApp'));

  // initialize a new instance of the filter before each test
  var formatDuration;
  beforeEach(inject(function ($filter) {
    formatDuration = $filter('formatDuration');
  }));

  it('should return the input prefixed with "formatDuration filter:"', function () {
    var text = 'angularjs';
    expect(formatDuration(text)).toBe('formatDuration filter: ' + text);
  });

});
