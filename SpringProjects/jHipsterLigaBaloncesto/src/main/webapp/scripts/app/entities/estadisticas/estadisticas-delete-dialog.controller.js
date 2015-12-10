'use strict';

angular.module('jHipsterLigaBaloncestoApp')
	.controller('EstadisticasDeleteController', function($scope, $modalInstance, entity, Estadisticas) {

        $scope.estadisticas = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Estadisticas.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });