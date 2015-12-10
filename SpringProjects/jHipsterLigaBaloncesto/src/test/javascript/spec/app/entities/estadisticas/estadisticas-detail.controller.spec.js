'use strict';

describe('Estadisticas Detail Controller', function() {
    var $scope, $rootScope;
    var MockEntity, MockEstadisticas, MockJugador, MockPartido;
    var createController;

    beforeEach(inject(function($injector) {
        $rootScope = $injector.get('$rootScope');
        $scope = $rootScope.$new();
        MockEntity = jasmine.createSpy('MockEntity');
        MockEstadisticas = jasmine.createSpy('MockEstadisticas');
        MockJugador = jasmine.createSpy('MockJugador');
        MockPartido = jasmine.createSpy('MockPartido');
        

        var locals = {
            '$scope': $scope,
            '$rootScope': $rootScope,
            'entity': MockEntity ,
            'Estadisticas': MockEstadisticas,
            'Jugador': MockJugador,
            'Partido': MockPartido
        };
        createController = function() {
            $injector.get('$controller')("EstadisticasDetailController", locals);
        };
    }));


    describe('Root Scope Listening', function() {
        it('Unregisters root scope listener upon scope destruction', function() {
            var eventType = 'jHipsterLigaBaloncestoApp:estadisticasUpdate';

            createController();
            expect($rootScope.$$listenerCount[eventType]).toEqual(1);

            $scope.$destroy();
            expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
        });
    });
});
