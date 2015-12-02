'use strict';

angular.module('jHipsterLigaBaloncestoApp')
	.controller('SocioDeleteController', function($scope, $modalInstance, entity, Socio) {

        $scope.socio = entity;
        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Socio.delete({id: id},
                function () {
                    $modalInstance.close(true);
                });
        };

    });