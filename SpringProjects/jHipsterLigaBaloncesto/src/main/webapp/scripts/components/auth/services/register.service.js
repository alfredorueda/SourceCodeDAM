'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


