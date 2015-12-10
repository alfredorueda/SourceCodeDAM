'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .factory('Partido', function ($resource, DateUtils) {
        return $resource('api/partidos/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.fechaPartido = DateUtils.convertDateTimeFromServer(data.fechaPartido);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
