'use strict';

describe('Service: AllocineWebService', function () {

  // load the service's module
  beforeEach(module('mediathequeUiApp'));

  // instantiate service
  var AllocineWebService;
  beforeEach(inject(function (_AllocineWebService_) {
    AllocineWebService = _AllocineWebService_;
  }));

  it('should do something', function () {
    expect(!!AllocineWebService).toBe(true);
  });

});
