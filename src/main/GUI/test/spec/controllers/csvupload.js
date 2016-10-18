'use strict';

describe('Controller: CsvuploadCtrl', function () {

  // load the controller's module
  beforeEach(module('mediathequeUiApp'));

  var CsvuploadCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CsvuploadCtrl = $controller('CsvuploadCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CsvuploadCtrl.awesomeThings.length).toBe(3);
  });
});
