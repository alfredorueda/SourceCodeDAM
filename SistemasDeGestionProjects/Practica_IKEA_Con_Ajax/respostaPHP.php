<?php
$action = $_GET['action'];

//$html="<ul id='items' data-role='listview' data-inset='true' data-filter='true' data-filter-placeholder='Introduce tu búsqueda...'><li data-role='list-divider'>Comedor</li><li><a href='#detailsSofaGrande'><img src='img/Sofa.jpg'>Sofa grande</a></li><li><a href='#'><img src='img/Mesita.png'>Mesa comedor</a></li><li data-role='list-divider'>Dormitorio</li><li><a href='#'><img src='img/Mesita.png'>Monstruo bajo la cama</a></li><li><a href='#'><img src='img/Mesita.png'>Escudo</a></li></ul>";
$html="<li data-role='list-divider'>Comedor</li><li><a href='#detailsSofaGrande'><img src='img/Sofa.jpg'>Sofa grande</a></li><li><a href='#'><img src='img/Mesita.png'>Mesa comedor</a></li><li data-role='list-divider'>Dormitorio</li><li><a href='#'><img src='img/Mesita.png'>Monstruo bajo la cama</a></li><li><a href='#'><img src='img/Mesita.png'>Escudo</a></li>";

$resposta='{"codi":"'.$html.'"}';

if(isset($_GET['callback'])){
   echo $_GET['callback'].'('. $resposta.')';
}
else {
    echo ($resposta);
}
?>
