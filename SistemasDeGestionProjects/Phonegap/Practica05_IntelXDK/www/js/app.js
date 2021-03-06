/*
 * Please see the included README.md file for license terms and conditions.
 */


// This file is a suggested starting place for your code.
// It is completely optional and not required.
// Note the reference that includes it in the index.html file.


/*jslint browser:true, devel:true, white:true, vars:true */
/*global $:false, intel:false app:false, dev:false, cordova:false */


// For improved debugging and maintenance of your app, it is highly
// recommended that you separate your JavaScript from your HTML files.
// Use the addEventListener() method to associate events with DOM elements.

// For example:

// var el ;
// el = document.getElementById("id_myButton") ;
// el.addEventListener("click", myEventHandler, false) ;



// The function below is an example of the best way to "start" your app.
// This example is calling the standard Cordova "hide splashscreen" function.
// You can add other code to it or add additional functions that are triggered
// by the same event or other events.

function onAppReady() {
    if( navigator.splashscreen && navigator.splashscreen.hide ) {   // Cordova API detected
        navigator.splashscreen.hide() ;
    }
    
    cont = 0
    
    //Ejercicio 1
    $('#buttonNotifications').click(function(){
        var numeroNotificaciones = prompt("Entra el número de sonidos que quieres reproducir");
        navigator.notification.beep(parseInt(numeroNotificaciones));
    });
    
    //Ejercicio 2
    $('#buttonVibrate').click(function(){
        var numeroVibraciones = $('#numeroVibraciones').val();
        if (numeroVibraciones < 0 || numeroVibraciones > 5){
            alert("El mínimo es 0 y el máximo 5, listillo.");
        } else {
            counter = numeroVibraciones;
            id = setInterval(function() {
                if (counter == 0) {
                    clearInterval(id);
                } else {
                    counter--;
                    navigator.notification.vibrate(500);
                } 
            }, 1000);
        }
    });
    //Ejercicio 3
    //Crear un boton para lanzar esto
    
    $('#mostraFoto').click(function(){
        navigator.camera.getPicture(onSuccess, onFail, { 
        quality : 75, 
        destinationType : Camera.DestinationType.DATA_URL, 
        sourceType : Camera.PictureSourceType.PHOTOLIBRARY, 
        allowEdit : true,
        encodingType: Camera.EncodingType.JPEG,
        targetWidth: 100,
        targetHeight: 100 
        }); 
    });

    function onSuccess(imageData) {
        rand = Math.floor((Math.random() * 1000) + 1);
        rand2 = Math.floor((Math.random() * 1000) + 1);
        $('#fotosMostrades').append('<img id="foto' + rand + rand2 + '" style="width:100px;height:100px;"/>');
        $('#foto'+rand + rand2).attr("src", "data:image/jpeg;base64," + imageData);
    }

    function onFail(message) {
        alert('Failed because: ' + message);
    }
    
    
    
    //Ejercicio 4
    
    $('#ferFoto').click(function(){
        navigator.camera.getPicture(onSuccess2, onFail2, { quality : 75, 
        destinationType : Camera.DestinationType.DATA_URL, 
        sourceType : Camera.PictureSourceType.CAMERA, 
        allowEdit : true,
        encodingType: Camera.EncodingType.JPEG,
        targetWidth: 100,
        targetHeight: 100 
        }); 
    });

    function onSuccess2(imageData) {
        console.log("foto")
        rand3 = Math.floor((Math.random() * 1000) + 1);
        rand4 = Math.floor((Math.random() * 1000) + 1);
        $('#fotosCapturades').append('<img id="foto' + rand3 + rand4 + '" style="width:100px;height:100px;"/>');
        $('#foto'+rand3 + rand4).attr("src", "data:image/jpeg;base64," + imageData);
    }

    function onFail2(message) {
        alert('Failed because: ' + message);
    }
    
    //Ejercicio 5
    
    $('#imgBrujola').css({
            'width':'100px', 
            'height':'100px'
        });
        
    var options2 = { frequency: 1 }; 
    
    $('#mostraBrujola').click(function(){
        navigator.compass.watchHeading(onSuccess5, onError5, options2);
    });
    
    function onSuccess5(heading) {
        $('#divBrujola').html("Orientació: " + heading.magneticHeading);
        $('#imgBrujola').css({
            '-ms-transform': 'rotate(' + heading.magneticHeading + 'deg)', /* IE 9 */
            '-webkit-transform': 'rotate(' + heading.magneticHeading + 'deg)', /* Chrome, Safari, Opera */
            'transform': 'rotate(' + heading.magneticHeading + 'deg)'
        });
    };

    function onError5(error) {
        alert('CompassError: ' + error.code);
    };

    
    //Ejercicio 6
    
    $('#buttonAcceleracio').click(function(){
        
        function onSuccess6(acceleration) {
            $('#divAcceleracio').html('Aceleración X: ' + acceleration.x + '<br/>' +
                  'Aceleración Y: ' + acceleration.y + '<br/>' +
                  'Aceleración Z: ' + acceleration.z + '<br/>');
        };

        function onError6() {
            alert('onError!');
        };

        var options = { frequency: 1 };

        var watchID = navigator.accelerometer.watchAcceleration(onSuccess6, onError6, options);
        
        
        window.addEventListener("deviceorientation", controlOrientation);
        
        function controlOrientation(event){
            $('#divRotacio').html('Rotacións: <br/> X/Z: ' + event.gamma + '<br/>' +
                  'Y/Z: ' + event.beta + '<br/>' +
                  'Y/X: ' + event.alpha + '<br/>');
        }
        
        
    });
    
    //Ejercicio 7
    
    intel.xdk.device.setRotateOrientation('portrait');

    $('#playGame').click(function(){
        $('#playGame').hide();
        $('#pelota').css("left", 100);
        $('#pelota').css("top", 100);
        window.addEventListener("deviceorientation", controlOrientation7);
        
        function controlOrientation7(event){
          $('#pelota').css("left", parseInt(event.gamma)+90);
          $('#pelota').css("top", parseInt(event.beta)+90);
          
          if ((parseInt(event.gamma) > 20 && parseInt(event.beta) > 20) && (parseInt(event.gamma) < 70 && parseInt(event.beta) < 70)){
              window.removeEventListener("deviceorientation", controlOrientation7);
              $('#pelota').hide();
              alert("Game Over!");
          }
          
        }
        
        
    });
    
    
    
    
    //EXAMEN
    //Exercici 1
    var numBeeps = 1;
    $('#playSoundExam').click(function(){
        navigator.notification.beep(numBeeps);
        numBeeps = numBeeps + 1;
    });
    
    //Exercici 2
    var idImages = 0;
    $('#mostraImatgeExamen').click(function(){
        idImages = idImages + 1;
        navigator.camera.getPicture(onSuccessExamen, onFailExamen, { 
        quality : 75, 
        destinationType : Camera.DestinationType.DATA_URL, 
        sourceType : Camera.PictureSourceType.PHOTOLIBRARY, 
        allowEdit : true,
        encodingType: Camera.EncodingType.JPEG,
        targetWidth: 100,
        targetHeight: 100 
        }); 
    });

    function onSuccessExamen(imageData) {
        $('#imagenesExam').append('<img id="foto' + idImages + '"/>');
        $('#foto'+idImages).attr("src", "data:image/jpeg;base64," + imageData);
        $('#foto'+idImages).attr("width", "70px");
        $('#foto'+idImages).attr("height", "70px");
    }

    function onFailExamen(message) {
        alert('Failed because: ' + message);
    }
    
    //Exercici 3
    
    intel.xdk.device.setRotateOrientation('portrait');

    $('#rotarImatgesExamen').click(function(){
        
        window.addEventListener("deviceorientation", controlOrientationExamen);

        function controlOrientationExamen(event){
            $('#imagenesExam>img').each(function(){
                $(this).css({
                    '-ms-transform': 'rotate(' + event.gamma + 'deg)', /* IE 9 */
                    '-webkit-transform': 'rotate(' + event.gamma  + 'deg)', /* Chrome, Safari, Opera */
                    'transform': 'rotate(' + event.gamma  + 'deg)'
                });
            });
        }
    });
    
    //Exercici 4
    
    $('#editarImagenes').click(function(){
        $('#imagenesExam>img').each(function(){
            $(this).click(function(){
               $(this).remove();
            });
        });
    });
        
    
    
    
    
    
}
document.addEventListener("app.Ready", onAppReady, false) ;
// document.addEventListener("deviceready", onAppReady, false) ;
// document.addEventListener("onload", onAppReady, false) ;

// The app.Ready event shown above is generated by the init-dev.js file; it
// unifies a variety of common "ready" events. See the init-dev.js file for
// more details. You can use a different event to start your app, instead of
// this event. A few examples are shown in the sample code above. If you are
// using Cordova plugins you need to either use this app.Ready event or the
// standard Crordova deviceready event. Others will either not work or will
// work poorly.

// NOTE: change "dev.LOG" in "init-dev.js" to "true" to enable some console.log
// messages that can help you debug Cordova app initialization issues.
