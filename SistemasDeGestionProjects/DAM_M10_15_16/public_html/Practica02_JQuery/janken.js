/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(iniciar);
function iniciar(){
    $('#fight').click(startGame)
}

function startGame(){
    $('#jugador1 > img').css({
        'height'   : '120px',
        'position' : 'relative',
        'left'     : '0px',
        'top'      : '0px'
    });
    
    $('#jugador1 > h3').css({
        'background-color' : 'blue'
    });
    
    $('#jugador2 > img').hide();
    $('#fight').hide();
    $('#player1Win').hide();
    $('#player2Win').hide();
    $('#draw').hide();
    
    
//    $('jugador1 > img').each(function(){
//        $(this).mouseenter(function(){
//            $(this).animate({
//                'width'  : '140px',
//                'height' : '140px'
//            },{
//                'duration' : 200
//            })
//        });
//    });
    
//    $('jugador1 > img').each(){
//        $(this).mouseenter(function(){
//            $(this).animate({
//                'width'  : '140px',
//                'height' : '140px'
//            },{
//                'duration' : 200
//            })
//        });
//    }

//    $('#rock1').mouseenter(function(){
//        $(this).animate({
//            'width'  : '140px',
//            'height' : '140px'
//        },{
//            'duration' : 200
//        })
//    });
    
    
}