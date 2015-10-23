$(document).ready(iniciar);
function iniciar() {
    //vinculem els esdeveniments
    $("button").click(enviaAjaxJSON);

}
/*Envia el text introduït en el input com a valor
 * d'un parámetre "nombre"
 */
function enviaAjaxJSON() {
    var textInput = $("input").val(); //obtenim el text dins el input
    //creem la consulta Ajax i la enviem
    $.ajax({
        type: "POST",
        url: "respostaAjaxJSON.php", //a on enviarem la consulta
        dataType: "json",
        data: {nombre: textInput}, //conjunt de parámetres i valors que s'envien
        success: function (respJSON) { //funció que s'ha d'executar al rebre la resposta
            var nombre = respJSON.nombre;   //extraiem els valors de la resposta com si fosin atributs d'una clase
            var longitud = respJSON.longitud;
            
            //modifiquem el document per mostrar els valors
            $("#longitud").html(longitud); //.html() permet modificar o obtenir el contingut d'un element
            $("#texto").html(nombre);
        }
    });
    return false;
}

