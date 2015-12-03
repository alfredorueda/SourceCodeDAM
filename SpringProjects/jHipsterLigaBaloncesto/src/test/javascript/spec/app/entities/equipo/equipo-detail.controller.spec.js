'use strict';

describe('Equipo Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockEquipo, MockJugador, MockSocio, MockEntrenador, MockEstadio;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockEquipo = jasmine.createSpy('MockEquipo');
        MockJugador = jasmine.createSpy('MockJugador');
        MockSocio = jasmine.createSpy('MockSocio');
        MockEntrenador = jasmine.createSpy('MockEntrenador');
        MockEstadio = jasmine.createSpy('MockEstadio');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Equipo': MockEquipo,
            'Jugador': MockJugador,
            'Socio': MockSocio,
            'Entrenador': MockEntrenador,
            'Estadio': MockEstadio
        };
        createController = function() {
            $injector.get('$controller')("EquipoDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'jHipsterLigaBaloncestoApp:equipoUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
