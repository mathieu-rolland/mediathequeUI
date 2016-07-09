'use strict';

describe('Controller: SearchlocalmovietoallocineCtrl', function () {

  // load the controller's module
  beforeEach(module('mediathequeUiApp'));

  var SearchlocalmovietoallocineCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SearchlocalmovietoallocineCtrl = $controller('SearchlocalmovietoallocineCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SearchlocalmovietoallocineCtrl.awesomeThings.length).toBe(3);
  });
});
