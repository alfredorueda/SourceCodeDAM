'use strict';

describe('Jugador Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockJugador, MockEquipo, MockEstadisticas;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockJugador = jasmine.createSpy('MockJugador');
        MockEquipo = jasmine.createSpy('MockEquipo');
        MockEstadisticas = jasmine.createSpy('MockEstadisticas');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Jugador': MockJugador,
            'Equipo': MockEquipo,
            'Estadisticas': MockEstadisticas
        };
        createController = function() {
            $injector.get('$controller')("JugadorDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'jHipsterLigaBaloncestoApp:jugadorUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
