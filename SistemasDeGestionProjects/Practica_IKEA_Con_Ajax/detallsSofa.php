<?php
$action = $_GET['id'];

$html="<div><img src='img/Sofa.jpg' width='80' height='80'><img src='img/Sofa.jpg' width='80' height='80'><img src='img/Sofa.jpg' width='80' height='80'><img src='img/Sofa.jpg' width='80' height='80'></div><a href='' class='ui-btn'>ADD PICTURE</a><a href=''> COMEDOR </a><a href='' class='ui-btn'>sofá de piel</a><br/><a href='' class='ui-btn'>Anchura 20</a><a href='' class='ui-btn'>Altura 30</a><a href='' class='ui-btn'>Profundidad 10</a>";

$resposta='{"codi":"'.$html.'"}';

if(isset($_GET['callback']) && $action == 1){
   echo $_GET['callback'].'('. $resposta.')';
}
else {
    echo ($resposta);
}
?>
