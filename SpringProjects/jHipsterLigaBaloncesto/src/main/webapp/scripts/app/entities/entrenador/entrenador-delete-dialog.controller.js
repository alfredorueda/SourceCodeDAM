'use strict';

angular.module('jHipsterLigaBaloncestoApp')
	.controller('EntrenadorDeleteController', function($scope, $modalInstance, entity, Entrenador) {

        $scope.entrenador = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Entrenador.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });