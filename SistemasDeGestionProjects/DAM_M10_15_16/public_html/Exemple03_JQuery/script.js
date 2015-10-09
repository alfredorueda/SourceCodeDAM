/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(iniciar);
function iniciar(){
//    $('div').css('background-color','blue');
    
    $('div').css({
        'backgroud-color' : 'blue',
        'color'           : 'red'
    })
    
    $('#boton').click(changeColour);
}

function changeColour(){
    $(this).css({
        'background-color' : 'blue',
        'position'         : 'relative'
    })
    
    $(this).animate({
        'width'   : '150px',
        'left'    : '300px',
        'padding' : '10px',
    },{
        'duration' : 2000,
        'complete' : colorNuevo
    });
}

function colorNuevo(){
    $(this).css({'background-color' : 'red'})
}