<?php
$nom = $_GET['nom'];
$password = $_GET['password'];

if($nom=="dam2" && $password == "12345"){
    $resposta='{"login":"ok"}';
}else{
    $resposta='{"login":"no"}';
}


if(isset($_GET['callback'])){
   echo $_GET['callback'].'('. $resposta.')';
}
else {
    echo ($resposta);

}
