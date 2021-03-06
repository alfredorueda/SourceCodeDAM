'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('EquipoDetailController', function ($scope, $rootScope, $stateParams, entity, Equipo, Jugador, Socio, Entrenador, Estadio, Temporada, Partido) {
        $scope.equipo = entity;
        $scope.load = function (id) {
            Equipo.get({id: id}, function(result) {
                $scope.equipo = result;
            });
        };
        var unsubscribe = $rootScope.$on('jHipsterLigaBaloncestoApp:equipoUpdate', function(event, result) {
            $scope.equipo = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
