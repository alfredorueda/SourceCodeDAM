'use strict';

angular.module('basketballApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {});
    });


