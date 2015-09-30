/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var numero = window.prompt("Introduce un numero:");
numero = parseInt(numero);

document.write("<ul>");
for (var i = 0; i <= numero; i++){
    document.write("<li>" + i + "</li>");
}
document.write("</ul>");
