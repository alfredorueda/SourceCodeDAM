'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('EstadisticasDetailController', function ($scope, $rootScope, $stateParams, entity, Estadisticas, Jugador, Partido) {
        $scope.estadisticas = entity;
        $scope.load = function (id) {
            Estadisticas.get({id: id}, function(result) {
                $scope.estadisticas = result;
            });
        };
        var unsubscribe = $rootScope.$on('jHipsterLigaBaloncestoApp:estadisticasUpdate', function(event, result) {
            $scope.estadisticas = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
