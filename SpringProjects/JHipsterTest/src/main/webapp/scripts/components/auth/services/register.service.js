'use strict';

angular.module('jHipsterTestApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


