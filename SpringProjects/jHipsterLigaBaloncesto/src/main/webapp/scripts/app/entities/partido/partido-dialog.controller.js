'use strict';

angular.module('jHipsterLigaBaloncestoApp').controller('PartidoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Partido', 'Temporada', 'Arbitro', 'Equipo',
        function($scope, $stateParams, $modalInstance, entity, Partido, Temporada, Arbitro, Equipo) {

        $scope.partido = entity;
        $scope.temporadas = Temporada.query();
        $scope.arbitros = Arbitro.query();
        $scope.equipos = Equipo.query();
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
