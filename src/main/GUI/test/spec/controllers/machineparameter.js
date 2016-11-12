'use strict';

describe('Controller: MachineparameterCtrl', function () {

  // load the controller's module
  beforeEach(module('mediathequeUiApp'));

  var MachineparameterCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MachineparameterCtrl = $controller('MachineparameterCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
