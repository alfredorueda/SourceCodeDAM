'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('TemporadaDetailController', function ($scope, $rootScope, $stateParams, entity, Temporada, Liga, Equipo) {
        $scope.temporada = entity;
        $scope.load = function (id) {
            Temporada.get({id: id}, function(result) {
                $scope.temporada = result;
            });
        };
        var unsubscribe = $rootScope.$on('jHipsterLigaBaloncestoApp:temporadaUpdate', function(event, result) {
            $scope.temporada = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
