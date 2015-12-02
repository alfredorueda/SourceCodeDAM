'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('SocioDetailController', function ($scope, $rootScope, $stateParams, entity, Socio, Equipo) {
        $scope.socio = entity;
        $scope.load = function (id) {
            Socio.get({id: id}, function(result) {
                $scope.socio = result;
            });
        };
        var unsubscribe = $rootScope.$on('jHipsterLigaBaloncestoApp:socioUpdate', function(event, result) {
            $scope.socio = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
