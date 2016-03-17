'use strict';

angular.module('basketballApp').controller('PlayerDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Player',
        function ($scope, $stateParams, $uibModalInstance, entity, Player) {

            $scope.player = entity;
            $scope.load = function (id) {
                Player.get({id: id}, function (result) {
                    $scope.player = result;
                });
            };

            var onSaveSuccess = function (result) {
                $scope.$emit('basketballApp:playerUpdate', result);
                $uibModalInstance.close(result);
                $scope.isSaving = false;
            };

            var onSaveError = function (result) {
                $scope.isSaving = false;
            };

            $scope.save = function () {
                $scope.isSaving = true;
                if ($scope.player.id != null) {
                    Player.update($scope.player, onSaveSuccess, onSaveError);
                } else {
                    Player.save($scope.player, onSaveSuccess, onSaveError);
                }
            };

            $scope.clear = function () {
                $uibModalInstance.dismiss('cancel');
            };
        }]);
