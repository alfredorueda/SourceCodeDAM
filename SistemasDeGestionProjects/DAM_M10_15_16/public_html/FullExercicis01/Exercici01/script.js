/* 
 Un programa que demani un número al usuari y mostri per consola la progressió 
 dels números enters y parells des de 0 fins al número introduït.
 */

var esNumero = false;
var nombresParells = new Array();

while(esNumero == false){
    var numero= window.prompt("Introdueixi un numero enter:");
    if (isNaN(numero)){
        esNumero = false;
    } else {
        esNumero = true;
    }
}

numero = parseInt(numero);

for (var i = 0; i <= numero; i++){
    console.log(i);
    if (i%2 == 0){
        nombresParells.push(i);
    }
}

console.log(nombresParells)

