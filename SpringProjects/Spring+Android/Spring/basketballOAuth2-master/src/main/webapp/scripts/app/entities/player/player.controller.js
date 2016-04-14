'use strict';

angular.module('basketballApp')
    .controller('PlayerController', function ($scope, $state, $modal, Player) {
      
        $scope.players = [];
        $scope.loadAll = function() {
            Player.query(function(result) {
               $scope.players = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.player = {
                name: null,
                baskets: null,
                asistencias: null,
                rebotes: null,
                posicionCampo: null,
                id: null
            };
        };
    });
