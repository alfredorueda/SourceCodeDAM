'use strict';

angular.module('basketballApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function (response) {
                var alertKey = response.headers('X-basketballApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, {param: response.headers('X-basketballApp-params')});
                }
                return response;
            }
        };
    });
