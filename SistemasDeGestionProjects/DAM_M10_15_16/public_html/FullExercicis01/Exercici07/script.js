/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function suma(){
    var numbersArray = new Array();
    var esNumero = false;
    var cont = 0;
    
    while(esNumero == false){
        var numero = window.prompt("Introdueixi un numero enter:");
        if (isNaN(numero)){
            esNumero = false;
        } else {
            cont++;
            numero = parseInt(numero);
            numbersArray.push(numero);
        }
        if (cont == 2) {
            esNumero = true;
        }
    }
    
    var suma = parseInt(numbersArray[0]) + parseInt(numbersArray[1])
    
    document.getElementById("resultat").innerHTML = "El resultat de sumar " + numbersArray[0] + 
            " + " + numbersArray[1] + " es " + suma;
    
    document.getElementById("progressio").innerHTML = "";
}

function resta(){
    var numbersArray = new Array();
    var esNumero = false;
    var cont = 0;
    
    while(esNumero == false){
        var numero = window.prompt("Introdueixi un numero enter:");
        if (isNaN(numero)){
            esNumero = false;
        } else {
            cont++;
            numero = parseInt(numero);
            numbersArray.push(numero);
        }
        if (cont == 2) {
            esNumero = true;
        }
    }
    
    var resta = parseInt(numbersArray[0]) - parseInt(numbersArray[1])
    
    document.getElementById("resultat").innerHTML = "El resultat de restar " + numbersArray[0] + 
            " - " + numbersArray[1] + " es " + resta;
    
    document.getElementById("progressio").innerHTML = "";
}

function multiplica(){
    var numbersArray = new Array();
    var esNumero = false;
    var cont = 0;
    
    while(esNumero == false){
        var numero = window.prompt("Introdueixi un numero enter:");
        if (isNaN(numero)){
            esNumero = false;
        } else {
            cont++;
            numero = parseInt(numero);
            numbersArray.push(numero);
        }
        if (cont == 2) {
            esNumero = true;
        }
    }
    
    var multiplicacio = parseInt(numbersArray[0]) * parseInt(numbersArray[1])
    
    document.getElementById("resultat").innerHTML = "El resultat de multiplicar " + numbersArray[0] + 
            " * " + numbersArray[1] + " es " + multiplicacio;
    
    var cadToOutput = "<ul>";
    for (var i = numbersArray[0]; i <= multiplicacio; i = i + numbersArray[0]){
        cadToOutput = cadToOutput  + "<li>" + i + "</li>";
    }
    document.getElementById("progressio").innerHTML = cadToOutput + "</ul>";
}