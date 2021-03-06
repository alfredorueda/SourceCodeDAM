'use strict';

angular.module('jHipsterLigaBaloncestoApp').controller('ArbitroDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Arbitro', 'Liga',
        function($scope, $stateParams, $modalInstance, entity, Arbitro, Liga) {

        $scope.arbitro = entity;
        $scope.ligas = Liga.query();
        $scope.load = function(id) {
            Arbitro.get({id : id}, function(result) {
                $scope.arbitro = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('jHipsterLigaBaloncestoApp:arbitroUpdate', result);
            $modalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.arbitro.id != null) {
                Arbitro.update($scope.arbitro, onSaveSuccess, onSaveError);
            } else {
                Arbitro.save($scope.arbitro, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
