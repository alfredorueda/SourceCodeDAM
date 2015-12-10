'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('ArbitroDetailController', function ($scope, $rootScope, $stateParams, entity, Arbitro, Liga) {
        $scope.arbitro = entity;
        $scope.load = function (id) {
            Arbitro.get({id: id}, function(result) {
                $scope.arbitro = result;
            });
        };
        var unsubscribe = $rootScope.$on('jHipsterLigaBaloncestoApp:arbitroUpdate', function(event, result) {
            $scope.arbitro = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
