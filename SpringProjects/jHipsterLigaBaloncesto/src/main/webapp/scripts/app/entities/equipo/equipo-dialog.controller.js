'use strict';

angular.module('jHipsterLigaBaloncestoApp').controller('EquipoDialogController',
    ['$scope', '$stateParams', '$modalInstance', '$q', 'entity', 'Equipo', 'Jugador', 'Socio', 'Entrenador', 'Estadio', 'Temporada', 'Partido',
        function($scope, $stateParams, $modalInstance, $q, entity, Equipo, Jugador, Socio, Entrenador, Estadio, Temporada, Partido) {

        $scope.equipo = entity;
        $scope.jugadors = Jugador.query();
        $scope.socios = Socio.query();
        $scope.entrenadors = Entrenador.query({filter: 'equipo-is-null'});
        $q.all([$scope.equipo.$promise, $scope.entrenadors.$promise]).then(function() {
            if (!$scope.equipo.entrenador || !$scope.equipo.entrenador.id) {
                return $q.reject();
            }
            return Entrenador.get({id : $scope.equipo.entrenador.id}).$promise;
        }).then(function(entrenador) {
            $scope.entrenadors.push(entrenador);
        });
        $scope.estadios = Estadio.query({filter: 'equipo-is-null'});
        $q.all([$scope.equipo.$promise, $scope.estadios.$promise]).then(function() {
            if (!$scope.equipo.estadio || !$scope.equipo.estadio.id) {
                return $q.reject();
            }
            return Estadio.get({id : $scope.equipo.estadio.id}).$promise;
        }).then(function(estadio) {
            $scope.estadios.push(estadio);
        });
        $scope.temporadas = Temporada.query();
        $scope.partidos = Partido.query();
        $scope.load = function(id) {
            Equipo.get({id : id}, function(result) {
                $scope.equipo = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('jHipsterLigaBaloncestoApp:equipoUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.equipo.id != null) {
                Equipo.update($scope.equipo, onSaveSuccess, onSaveError);
            } else {
                Equipo.save($scope.equipo, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
