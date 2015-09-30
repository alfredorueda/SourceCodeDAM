/* 
Un programa que demani un número al usuari y imprimeixi en el HTML una llista 
amb la progressió dels valors des de 0 fins al número introduït.
ex: <ul>
<li>0</li>
<li>1</li>
<li>2</li>
</ul>
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

document.write("<ul>");
for (var i = 0; i <= numero; i++){
    document.write("<li>" + i + "</li>");
}
document.write("</ul>");


