/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var esNumero = false;

while(esNumero == false){
    var numero= window.prompt("Introdueixi un numero enter:");
    if (isNaN(numero)){
        esNumero = false;
    } else {
        esNumero = true;
    }
}

numero = parseInt(numero);

for (i=numero; i>=0; i--){
    console.log(i);
}

if (numero > 20){
    console.log("El nombre introduït es major a 20:" + numero + "");
    document.writeln("<div>El nombre introduït es major a 20:" + numero + "</div>");
//    Solo sirve si el javascript se carga al final de la web, si no intentas acceder a un elemento
//    que el DOM aún no ha creado.
//    document.getElementById("test").innerHTML = "El nombre introduït es major a 20"
}


var esNumero2 = false;

while(esNumero2 == false){
    var numero2= window.prompt("Introdueixi un numero multiple de 3:");
    if (isNaN(numero)){
        esNumero2 = false;
    } else {
        if (numero2 % 3 == 0){
            esNumero2 = true;
            alert("El nombre " + numero2 + " es modul de 3.")
        } else {
            esNumero2 = false;
        }
    }
}