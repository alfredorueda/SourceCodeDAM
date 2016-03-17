'use strict';

angular.module('basketballApp')
    .factory('Password', function ($resource) {
        return $resource('api/account/change_password', {}, {});
    });

angular.module('basketballApp')
    .factory('PasswordResetInit', function ($resource) {
        return $resource('api/account/reset_password/init', {}, {})
    });

angular.module('basketballApp')
    .factory('PasswordResetFinish', function ($resource) {
        return $resource('api/account/reset_password/finish', {}, {})
    });
