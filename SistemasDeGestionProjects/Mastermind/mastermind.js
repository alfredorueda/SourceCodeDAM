/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var cont = 1;

$(document).ready(iniciar);
$("#set_password").click(enviaAjaxJSON);

function iniciar(){
    $('#start').click(function(){
        $('#find_password').hide();
        $(this).hide();
        startGame();
    });
}

function startGame(){
    
    $('.opcions > div').each(function(){
        $(this).click(function(){
            $(this).removeClass('opc' + (cont-1));
            if (cont > 4){
                cont = 1;
            }
            $(this).addClass('opc' + cont);
            cont++;
        });
    });
}

function enviarAjaxJSON(){
    
}