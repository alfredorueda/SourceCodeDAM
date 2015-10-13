/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(iniciar);

function iniciar(){
    primeraSeleccion = "";
    segundaSeleccion = "";
    $('#fight').click(startGame)
}

function startGame(){
    $('#jugador1 > img').css({
        'height'   : '120px',
        'position' : 'relative',
        'left'     : '0px',
        'top'      : '0px'
    });
    $('#jugador2 > img').css({
        'height'   : '120px',
        'position' : 'relative',
        'left'     : '0px',
        'top'      : '0px'
    });
    
    $('#jugador1 > h3').css({
        'background-color' : 'blue'
    });
    
    $('#jugador1 > img').show();
    $('#jugador2 > img').hide();
    $('#fight').hide();
    $('#player1Win').hide();
    $('#player2Win').hide();
    $('#draw').hide();
    $('#empate').remove();
    
    
    $('#jugador1 > img').each(function(){
        $(this).mouseenter(function(){         
            $(this).animate({
                'width'  : '140px',
                'height' : '140px'
            },{
                'duration' : 200
            });
            
            $(this).siblings('img').animate({
                'width'  : '100px',
                'height' : '100px'
            },{
                'duration' : 200
            });
        });
        
        $(this).mouseleave(function(){
            $('#jugador1 > img').animate({
                'width'  : '120px',
                'height' : '120px'
            },{
                'duration' : 200
            });
        });
    });
    
    $('#jugador1 > img').each(function(){
        $(this).click(function(){
            primeraSeleccion = $(this).attr('alt');
            console.log(primeraSeleccion);
            $('#jugador1 > img').off('click');
            $('#jugador1 > img').off('mouseenter');
            $('#jugador1 > img').off('mouseleave');
            $('#jugador1 > img').hide();
            turnoSegundoJugador();
        });
    });
}

function turnoSegundoJugador(){
    
    $('#jugador1 > h3').css({
        'background-color' : ''
    });
    
    $('#jugador2 > h3').css({
        'background-color' : 'blue'
    });
    
    $('#jugador2 > img').show();
    
     //Falta que se reduzcan los otros a 100px
    $('#jugador2 > img').each(function(){
        $(this).mouseenter(function(){         
            $(this).animate({
                'width'  : '140px',
                'height' : '140px'
            },{
                'duration' : 200
            });
            
            $(this).siblings('img').animate({
                    'width'  : '100px',
                    'height' : '100px'
                },{
                    'duration' : 200
                });
        });
        
        $(this).mouseleave(function(){
            $('#jugador2 > img').animate({
                'width'  : '120px',
                'height' : '120px'
            },{
                'duration' : 200
            })
        });
    });
    
    $('#jugador2 > img').each(function(){
        $(this).click(function(){
            segundaSeleccion = $(this).attr('alt');
            console.log(segundaSeleccion);
            $('#jugador2 > img').off('click');
            $('#jugador2 > img').off('mouseenter');
            $('#jugador2 > img').off('mouseleave');
            $('#jugador2 > img').hide();
            $('#jugador2 > h3').css({
                'background-color' : ''
            });
            resolverPartida();
        });
    });
}

function resolverPartida(){
    switch(primeraSeleccion){
        case 'rock':
            if (segundaSeleccion === 'scissors'){
                $('#fight').show();
                $('#player1Win').show();
                $('#jugador1 > img#rock1').show();
                $('#jugador2 > img#scissor2').show();
                break;
            }
            if (segundaSeleccion === 'paper'){
                $('#fight').show();
                $('#player2Win').show();
                $('#jugador1 > img#rock1').show();
                $('#jugador2 > img#paper2').show();
                break;
            }
            if (segundaSeleccion === 'rock'){
                //Empate
                $('#fight').show();
                $('#resultats').append("<div id='empate'>EMPATE</div>");
                $('#jugador1 > img#rock1').show();
                $('#jugador2 > img#rock2').show();
                break;
            }
        case 'paper':
            if (segundaSeleccion === 'scissors'){
                $('#fight').show();
                $('#player2Win').show();
                $('#jugador1 > img#paper1').show();
                $('#jugador2 > img#scissor2').show();
                break;
            }
            if (segundaSeleccion === 'paper'){
                //Empate
                $('#fight').show();
                $('#resultats').append("<div id='empate'>EMPATE</div>");
                $('#jugador1 > img#paper1').show();
                $('#jugador2 > img#paper2').show();
                break;
            }
            if (segundaSeleccion === 'rock'){
                $('#fight').show();
                $('#player1Win').show();
                $('#jugador1 > img#paper1').show();
                $('#jugador2 > img#rock2').show();
                break;
            }
        case 'scissors':
            if (segundaSeleccion === 'scissors'){
                //Empate
                $('#fight').show();
                $('#resultats').append("<div id='empate'>EMPATE</div>");
                $('#jugador1 > img#scissor1').show();
                $('#jugador2 > img#scissor2').show();
                break;
            }
            if (segundaSeleccion === 'paper'){
                $('#fight').show();
                $('#player1Win').show();
                $('#jugador1 > img#scissor1').show();
                $('#jugador2 > img#paper2').show();
                break;
            }
            if (segundaSeleccion === 'rock'){
                $('#fight').show();
                $('#player2Win').show();
                $('#jugador1 > img#scissor1').show();
                $('#jugador2 > img#rock2').show();
                break;
            }
    }
    
    $('#jugador1 > img').each(function(){
        if ($(this).attr('style').search('inline-block') != -1){
            $(this).animate({
                'position' : 'absolute',
                'left'     : '150px',
                'top'      : '150px'
            },{
                'duration' : 200
            });
        }
    });
    
    $('#jugador2 > img').each(function(){
        if ($(this).attr('style').search('block') != -1){
            $(this).animate({
                'position' : 'absolute',
                'left'     : '-150px',
                'top'      : '150px'
            },{
                'duration' : 200
            });
        }
    });
}