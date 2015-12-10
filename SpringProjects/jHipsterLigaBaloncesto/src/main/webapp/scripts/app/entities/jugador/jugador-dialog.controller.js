'use strict';

angular.module('jHipsterLigaBaloncestoApp').controller('JugadorDialogController',
    ['$scope', '$stateParams', '$modalInstance', '$q', 'entity', 'Jugador', 'Equipo', 'Estadisticas',
        function($scope, $stateParams, $modalInstance, $q, entity, Jugador, Equipo, Estadisticas) {

        $scope.jugador = entity;
        $scope.captains = Equipo.query({filter: 'jugador-is-null'});
        $q.all([$scope.jugador.$promise, $scope.captains.$promise]).then(function() {
            if (!$scope.jugador.captain || !$scope.jugador.captain.id) {
                return $q.reject();
            }
            return Equipo.get({id : $scope.jugador.captain.id}).$promise;
        }).then(function(captain) {
            $scope.captains.push(captain);
        });
        $scope.equipos = Equipo.query();
        $scope.estadisticass = Estadisticas.query();
        $scope.load = function(id) {
            Jugador.get({id : id}, function(result) {
                $scope.jugador = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('jHipsterLigaBaloncestoApp:jugadorUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.jugador.id != null) {
                Jugador.update($scope.jugador, onSaveSuccess, onSaveError);
            } else {
                Jugador.save($scope.jugador, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
