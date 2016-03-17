'use strict';

angular.module('basketballApp')
    .controller('ConfigurationController', function ($scope, ConfigurationService) {
        ConfigurationService.get().then(function (configuration) {
            $scope.configuration = configuration;
        });
    });
