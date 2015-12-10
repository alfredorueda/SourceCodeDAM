'use strict';

angular.module('jHipsterLigaBaloncestoApp').controller('EstadisticasDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Estadisticas', 'Jugador', 'Partido',
        function($scope, $stateParams, $modalInstance, entity, Estadisticas, Jugador, Partido) {

        $scope.estadisticas = entity;
        $scope.jugadors = Jugador.query();
        $scope.partidos = Partido.query();
        $scope.load = function(id) {
            Estadisticas.get({id : id}, function(result) {
                $scope.estadisticas = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('jHipsterLigaBaloncestoApp:estadisticasUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.estadisticas.id != null) {
                Estadisticas.update($scope.estadisticas, onSaveSuccess, onSaveError);
            } else {
                Estadisticas.save($scope.estadisticas, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
