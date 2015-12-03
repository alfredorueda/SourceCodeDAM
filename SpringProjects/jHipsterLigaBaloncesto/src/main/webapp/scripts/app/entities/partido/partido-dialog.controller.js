'use strict';

angular.module('jHipsterLigaBaloncestoApp').controller('PartidoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Partido', 'Estadisticas',
        function($scope, $stateParams, $modalInstance, entity, Partido, Estadisticas) {

        $scope.partido = entity;
        $scope.estadisticass = Estadisticas.query();
        $scope.load = function(id) {
            Partido.get({id : id}, function(result) {
                $scope.partido = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('jHipsterLigaBaloncestoApp:partidoUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.partido.id != null) {
                Partido.update($scope.partido, onSaveSuccess, onSaveError);
            } else {
                Partido.save($scope.partido, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
