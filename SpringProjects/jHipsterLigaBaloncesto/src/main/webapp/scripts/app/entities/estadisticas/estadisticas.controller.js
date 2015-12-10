'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .controller('EstadisticasController', function ($scope, $state, $modal, Estadisticas, ParseLinks) {
      
        $scope.estadisticass = [];
        $scope.page = 0;
        $scope.loadAll = function() {
            Estadisticas.query({page: $scope.page, size: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.estadisticass = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.estadisticas = {
                canastas: null,
                faltas: null,
                id: null
            };
        };
    });
