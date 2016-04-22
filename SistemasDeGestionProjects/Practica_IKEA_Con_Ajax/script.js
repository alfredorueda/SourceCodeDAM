//Exercici 1
$(document).on("click", "#loginForm", function () {
    var nom=$("#nomForm").val();
    var password=$('#passwordForm').val();
    $.ajax({
        url: "http://localhost/Practica_IKEA_Con_Ajax/login.php",
        dataType: "jsonp",
        jsonp: "callback",
        data: {"action": "getElement","nom":nom, "password":password},
        success: function (respJSON) {
            console.log(respJSON);
            var login = respJSON.login;
            if(login=="ok"){
                $( ":mobile-pagecontainer" ).pagecontainer( "change", "#paginaPerComprar", { role: "page" } );
            } else {
                alert("El login es incorrecte. Torna a provar-ho!");
            }
        }
    });
});

//Exercici 2
$(document).on("pageinit", "#paginaPerComprar", function () {
    console.log("Click");
    $.ajax({
        url: "http://localhost/Practica_IKEA_Con_Ajax/respostaPHP.php",
        dataType: "jsonp",
        jsonp: "callback",
        data: {"action": "getElement"},
        success: function (respJSON) {
            console.log(respJSON.codi);
            $('#items').html(respJSON.codi);
            $('#items').listview('refresh');
        }
    });
});

//Exercici 3
$(document).on("click", "#sofaId", function () {
    var sofaGrandeId = $('#sofaId>a').text();
    if (sofaGrandeId == "Sofa grande") {
        sofaGrandeId = 1;
    } else {
        sofaGrandeId = 0;
    }
    $.ajax({
        url: "http://localhost/Practica_IKEA_Con_Ajax/detallsSofa.php",
        dataType: "jsonp",
        jsonp: "callback",
        data: {"id":sofaGrandeId},
        success: function (respJSON) {
            console.log(respJSON);
            $('#detallsSofa').html("");
            $('#detallsSofa').append("<div>"+respJSON.image+respJSON.image+respJSON.image+respJSON.image+"</div>");
            $('#detallsSofa').append(respJSON.btnAddPicture);
            $('#detallsSofa').append(respJSON.labelComedor);
            $('#detallsSofa').append(respJSON.descripcion);
            $('#detallsSofa').append(respJSON.anchura);
            $('#detallsSofa').append(respJSON.altura);
            $('#detallsSofa').append(respJSON.profundidad);
        }
    });
});

//Exercici 4
$(document).on("click", "#addElementToTheList", function () {
    var ubicacion = $('#grid-select-1').val();
    var descripcion = $('#descripcionProducto').val();
    var anchura = $('#slider-1').val();
    var altura = $('#slider-2').val();
    var profundidad = $('#slider-3').val();

    console.log(ubicacion);
    console.log(descripcion);
    console.log(anchura);
    console.log(altura);
    console.log(profundidad);

    $('#items').append("<li data-role='list-divider>"+ ubicacion +"</li><li><a href='#detailsSofaGrande'><img src='img/Sofa.jpg'>" + descripcion + "</a></li>");
    $('#items').listview('refresh');

});