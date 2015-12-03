'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .factory('Estadisticas', function ($resource, DateUtils) {
        return $resource('api/estadisticass/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
