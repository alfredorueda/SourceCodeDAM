/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var cont = 1;

$(document).ready(iniciar);


function iniciar(){
    $('#set_password').click(enviarAjaxJSON);
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
    $.ajax({
        type    : "POST",
        url     : "checkMastermind.php",
        dataType: "json",
        data    : {
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