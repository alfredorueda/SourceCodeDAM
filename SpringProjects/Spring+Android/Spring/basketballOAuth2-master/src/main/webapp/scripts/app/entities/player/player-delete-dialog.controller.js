'use strict';

angular.module('basketballApp')
	.controller('PlayerDeleteController', function($scope, $modalInstance, entity, Player) {

        $scope.player = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Player.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });