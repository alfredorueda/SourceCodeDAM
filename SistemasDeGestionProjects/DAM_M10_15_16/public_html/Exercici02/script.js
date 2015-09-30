/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ejecutaFunction(cantidadDeNumeros){
    var numbers = new Array()
    var maxNumber = Number.MIN_VALUE;
    var minNumber = Number.MAX_VALUE;
    
    for (var i = 1; i <= cantidadDeNumeros; i++){
        numbers[i] = parseInt(window.prompt("Introduce un numero " + i + " de " + cantidadDeNumeros + ":"));
        
        if (numbers[i] > maxNumber){
            maxNumber = numbers[i];
        }
        
        if (numbers[i] < minNumber){
            minNumber = numbers[i];
        }
    }
    
    console.log("maxNumber:" + maxNumber);
    console.log("minNumber:" + minNumber);
}