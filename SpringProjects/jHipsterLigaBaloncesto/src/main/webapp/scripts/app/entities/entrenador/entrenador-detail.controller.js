'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('EntrenadorDetailController', function ($scope, $rootScope, $stateParams, entity, Entrenador, Equipo) {
        $scope.entrenador = entity;
        $scope.load = function (id) {
            Entrenador.get({id: id}, function(result) {
                $scope.entrenador = result;
            });
        };
        var unsubscribe = $rootScope.$on('jHipsterLigaBaloncestoApp:entrenadorUpdate', function(event, result) {
            $scope.entrenador = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
