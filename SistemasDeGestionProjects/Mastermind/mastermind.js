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
            
            if($(this).attr('class') == null){
                cont = 1;
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
            var actualAnswer = $('#resultats').html();
            
            $('#resultats').append("<div class='opcions'></div>");
            lastOptions = $('.opcions').last();
            for (elem in respuesta){
                if (elem == "op") {
                    continue
                }
                if (respuesta[elem] != 0){
                    lastOptions.append("<div class='" + respuesta[elem] +"' style='margin:3px;'></div>");
                } 
                if (respuesta[elem] == 0){
                    lastOptions.append("<div style='border-color:red; margin:3px;'</div>");
                }
            }
            
            if (respuesta.op1 !=0 && respuesta.op2 !=0 && respuesta.op3 !=0 && respuesta.op4 !=0){
                $('#check_password').hide();
                alert("Has guanyat!")
            }
        }
    });
    return false;
    }
}