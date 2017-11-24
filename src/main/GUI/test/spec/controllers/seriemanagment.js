'use strict';

describe('Controller: SeriemanagmentCtrl', function () {

  // load the controller's module
  beforeEach(module('mediathequeUiApp'));

  var SeriemanagmentCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SeriemanagmentCtrl = $controller('SeriemanagmentCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SeriemanagmentCtrl.awesomeThings.length).toBe(3);
  });
});
