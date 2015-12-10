'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('PartidoDetailController', function ($scope, $rootScope, $stateParams, entity, Partido, Temporada, Arbitro, Equipo, Estadisticas) {
        $scope.partido = entity;
        $scope.load = function (id) {
            Partido.get({id: id}, function(result) {
                $scope.partido = result;
            });
        };
        var unsubscribe = $rootScope.$on('jHipsterLigaBaloncestoApp:partidoUpdate', function(event, result) {
            $scope.partido = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
