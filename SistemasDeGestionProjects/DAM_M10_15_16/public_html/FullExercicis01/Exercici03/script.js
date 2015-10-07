/* 
Un programa que demani dos números per pantalla i ens indiqui quin dels 
dos és superior i quin és inferior.
 */

function ejecutaFunction(cantidadDeNumeros){
    var numbers = new Array()
    var maxNumber = Number.MIN_VALUE;
    var minNumber = Number.MAX_VALUE;
    
    for (var i = 1; i <= cantidadDeNumeros; i++){
        var esNumero = false;
        while(esNumero == false){
            var numero= window.prompt("Introduce un numero " + i + " de " + cantidadDeNumeros + ":");
            if (isNaN(numero)){
                esNumero = false;
            } else {
                esNumero = true;
            }
        }

        numbers[i] = parseInt(numero);
        
        if (numbers[i] > maxNumber){
            maxNumber = numbers[i];
        }
        
        if (numbers[i] < minNumber){
            minNumber = numbers[i];
        }
    }
    
    document.write("maxNumber:" + maxNumber + "<br/>");
    document.write("minNumber:" + minNumber);
}


