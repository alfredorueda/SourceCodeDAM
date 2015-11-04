/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var cont = 1;

$(document).ready(iniciar);


function iniciar(){
    $('#set_password').click(enviarAjaxJSON);
    $('#check_password').click(checkPassword);
    $('#start').click(function(){
        $('#find_password').hide();
        $(this).hide();
        startGame();
    });
}

function startGame(){
    $('.opcions > div').each(function(){
        $(this).click(function(){
            for (i=1; i<=4; i++){
                $(this).removeClass('opc' + i);
            }
            if (cont > 4){
                cont = 1;
            }
            $(this).addClass('opc' + cont);
            cont++;
        });
    });
}

function enviarAjaxJSON(){
    
    if ($('#pas1').attr('class') == null || $('#pas2').attr('class') == null || $('#pas3').attr('class') == null || $('#pas4').attr('class') == null){
        alert("La password no pot ser buida!")
    } else {
        $.ajax({
        type    : "POST",
        url     : "checkMastermind.php",
        dataType: "json",
        data    : {
                    op : "setPassword",
                    op1: $('#pas1').attr('class'),
                    op2: $('#pas2').attr('class'),
                    op3: $('#pas3').attr('class'),
                    op4: $('#pas4').attr('class')
                },
        success : function(){
            $('#crea_password').hide();
            $('#find_password').show();
        }
    });
    return false;
    }   
}

function checkPassword(){
    
    if ($('#op1').attr('class') == null || $('#op2').attr('class') == null || $('#op3').attr('class') == null || $('#op4').attr('class') == null){
        alert("La password no pot ser buida!")
    } else {
        $.ajax({
        type    : "POST",
        url     : "checkMastermind.php",
        dataType: "json",
        data    : {
                    op1: $('#op1').attr('class'),
                    op2: $('#op2').attr('class'),
                    op3: $('#op3').attr('class'),
                    op4: $('#op4').attr('class')
                },
        success : function(respuesta){
            var htmlToPrint;
            htmlToPrint = "<ul style='list-style-type: none; padding-left: 0px;'>";
            if (respuesta.op1 != 0){
                htmlToPrint = htmlToPrint + "<li>Valor 1: Correcto</li>";
            } else {
                htmlToPrint = htmlToPrint + "<li style='color:red'>Valor 1: Incorrecto</li>";
            }
            if (respuesta.op2 != 0){
                htmlToPrint = htmlToPrint + "<li>Valor 2: Correcto</li>";
            } else {
                htmlToPrint = htmlToPrint + "<li style='color:red'>Valor 2: Incorrecto</li>";
            }
            if (respuesta.op3 != 0){
                htmlToPrint = htmlToPrint + "<li>Valor 3: Correcto</li>";
            } else {
                htmlToPrint = htmlToPrint + "<li style='color:red'>Valor 3: Incorrecto</li>";
            }
            if (respuesta.op4 != 0){
                htmlToPrint = htmlToPrint + "<li>Valor 4: Correcto</li>";
            } else {
                htmlToPrint = htmlToPrint + "<li style='color:red'>Valor 4: Incorrecto</li>";
            }

            $('#resultats').html(htmlToPrint + "</ul>");
            console.log(respuesta);
        }
    });
    return false;
    }
    
    
    
}