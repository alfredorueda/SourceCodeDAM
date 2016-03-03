/**
 * Created by Javi on 27/01/2016.
 */
'use strict'
angular.module('ligaBaloncestoApp')
    .controller('jugadorCtrlByBaskets', function($scope, $http, entity, Jugador) {

        entity.$promise.then(function(data){
           $scope.jugadores = data;
            console.log(data)
        });


        //$scope.bestPlayersByBaskets = function () {
        //    Jugador.bestPlayersByBaskets($scope.cantidadCanastas);
        //};

        //$scope.jugadores = entity;
    });
