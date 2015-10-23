<?php

//obtenim els valors dels parámetres amb $_POST[]
$nombre = $_POST['nombre'];

$logitud = strlen($nombre); //calculem la longitud
//generem el jSON amb la resposta
$resposta = '{"nombre":"' . $nombre . '",
                "longitud":"' . $logitud . '"
            }';
echo ($resposta);
?>
