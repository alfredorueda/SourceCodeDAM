/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var vidasRestantes =3;
var numero= window.prompt("introdueixi numero");

while (isNaN(numero)){
    numero = parseInt(window.prompt("introdueixi numero"));
}

var resultat;
resultat = numero+vidasRestantes;
alert("Vides Restants:"+resultat); console.log("El numero introduit es"+numero);