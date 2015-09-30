/* 
Un programa que ens demani el nom del dia de la setmana 
i ens retorni el número del dia de la setmana començant per dilluns.
 */

var diaSetmana = window.prompt("Introdueix el dia de la setmana:");
diaSetmana = diaSetmana.toLowerCase();

switch (diaSetmana){
    case diesCorrectes[0]:
        document.write("Has introduit " + diaSetmana);
        break;
    case diesCorrectes[1]:
        document.write("Has introduit " + diaSetmana);
        break;
    case diesCorrectes[2]:
        document.write("Has introduit " + diaSetmana);
        break;
    case diesCorrectes[3]:
        document.write("Has introduit " + diaSetmana);
        break;
    case diesCorrectes[4]:
        document.write("Has introduit " + diaSetmana);
        break;
    case diesCorrectes[5]:
        document.write("Has introduit " + diaSetmana);
        break;
    case diesCorrectes[6]:
        document.write("Has introduit " + diaSetmana);
        break;
    default:
        document.write("El dia introduït no es correcte.");
        break;
}
