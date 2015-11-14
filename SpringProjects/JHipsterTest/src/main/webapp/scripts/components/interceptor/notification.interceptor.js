 'use strict';

angular.module('jHipsterTestApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-jHipsterTestApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-jHipsterTestApp-params')});
                }
                return response;
            }
        };
    });
