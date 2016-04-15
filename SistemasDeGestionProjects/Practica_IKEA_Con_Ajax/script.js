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
    console.log("EPA");

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
            $('#detallsSofa').html(respJSON.codi);
        }
    });
});

//Exercici 4
