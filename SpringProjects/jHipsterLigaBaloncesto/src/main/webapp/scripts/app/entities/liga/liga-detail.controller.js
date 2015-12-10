'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('LigaDetailController', function ($scope, $rootScope, $stateParams, entity, Liga, Temporada, Arbitro) {
        $scope.liga = entity;
        $scope.load = function (id) {
            Liga.get({id: id}, function(result) {
                $scope.liga = result;
            });
        };
        var unsubscribe = $rootScope.$on('jHipsterLigaBaloncestoApp:ligaUpdate', function(event, result) {
            $scope.liga = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
