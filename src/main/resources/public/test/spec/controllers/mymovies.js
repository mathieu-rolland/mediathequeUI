'use strict';

describe('Controller: MymoviesCtrl', function () {

  // load the controller's module
  beforeEach(module('mediathequeUiApp'));

  var MymoviesCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MymoviesCtrl = $controller('MymoviesCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});