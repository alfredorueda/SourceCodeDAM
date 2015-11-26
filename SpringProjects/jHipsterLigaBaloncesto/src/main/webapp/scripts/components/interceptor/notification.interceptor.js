 'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-jHipsterLigaBaloncestoApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-jHipsterLigaBaloncestoApp-params')});
                }
                return response;
            }
        };
    });
