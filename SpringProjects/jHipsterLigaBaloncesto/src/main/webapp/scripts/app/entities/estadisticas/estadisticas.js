'use strict';

angular.module('jHipsterLigaBaloncestoApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('estadisticas', {
                parent: 'entity',
                url: '/estadisticass',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'jHipsterLigaBaloncestoApp.estadisticas.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/estadisticas/estadisticass.html',
                        controller: 'EstadisticasController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('estadisticas');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('estadisticas.detail', {
                parent: 'entity',
                url: '/estadisticas/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'jHipsterLigaBaloncestoApp.estadisticas.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/estadisticas/estadisticas-detail.html',
                        controller: 'EstadisticasDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('estadisticas');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Estadisticas', function($stateParams, Estadisticas) {
                        return Estadisticas.get({id : $stateParams.id});
                    }]
                }
            })
            .state('estadisticas.new', {
                parent: 'estadisticas',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/estadisticas/estadisticas-dialog.html',
                        controller: 'EstadisticasDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    canastas: null,
                                    faltas: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('estadisticas', null, { reload: true });
                    }, function() {
                        $state.go('estadisticas');
                    })
                }]
            })
            .state('estadisticas.edit', {
                parent: 'estadisticas',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/estadisticas/estadisticas-dialog.html',
                        controller: 'EstadisticasDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Estadisticas', function(Estadisticas) {
                                return Estadisticas.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('estadisticas', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('estadisticas.delete', {
                parent: 'estadisticas',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/estadisticas/estadisticas-delete-dialog.html',
                        controller: 'EstadisticasDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Estadisticas', function(Estadisticas) {
                                return Estadisticas.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('estadisticas', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
