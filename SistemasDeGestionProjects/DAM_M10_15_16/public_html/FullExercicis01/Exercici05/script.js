/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var randomNumber = Math.floor((Math.random() * 100) + 1);
var numero = 0;
console.log(randomNumber)

while (randomNumber != numero){
    var esNumero = false;
    while(esNumero == false){
        var numero= window.prompt("Introdueixi un numero enter, entre 1 i 100:");
        if (isNaN(numero)){
            esNumero = false;
        } else {
            esNumero = true;
        }
    }

numero = parseInt(numero);
}

document.write("Has endevinat el nombre. El nombre era: " + randomNumber)



