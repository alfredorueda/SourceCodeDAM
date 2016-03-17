'use strict';

angular.module('basketballApp')
    .controller('PlayerDetailController', function ($scope, $rootScope, $stateParams, entity, Player) {
        $scope.player = entity;
        $scope.load = function (id) {
            Player.get({id: id}, function (result) {
                $scope.player = result;
            });
        };
        var unsubscribe = $rootScope.$on('basketballApp:playerUpdate', function (event, result) {
            $scope.player = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
