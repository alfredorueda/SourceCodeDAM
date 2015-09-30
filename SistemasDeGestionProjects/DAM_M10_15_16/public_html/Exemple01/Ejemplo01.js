/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var vidasRestantes = 3;
var resultat;
var esNumero = false;

while(esNumero == false){
    var numero= window.prompt("introdueixi numero");
    if (isNaN(numero)){
        esNumero = false;
    } else {
        esNumero = true;
    }
}

numero = parseInt(numero)
resultat = numero+vidasRestantes;
alert("Vides Restants:"+resultat); console.log("El numero introduit es"+numero);