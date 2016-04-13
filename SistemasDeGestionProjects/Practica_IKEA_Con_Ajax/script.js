//Login
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


$(document).on("click", "#comprarNavBar", function () {
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
