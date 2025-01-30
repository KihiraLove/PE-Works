<?php
    require('dbConnect.php');
    $db_connection = OpenDB();

    $chset=mysqli_query($db_connection, "SET CHARACTER SET 'utf8'");
    $chset2=mysqli_query($db_connection,"SET SESSION collation_connection ='utf8mb4_unicode_ci'");

    $feladat_tabla = mysqli_query($db_connection, "SELECT * FROM `feladat`");
    $hallgato_tabla = mysqli_query($db_connection, "SELECT * FROM `hallgato`");
    $reszfeladat_tabla = mysqli_query($db_connection, "SELECT * FROM `reszfeladat`");
    $szak_tabla = mysqli_query($db_connection, "SELECT * FROM `szak`");
    $szakvezeto_tabla = mysqli_query($db_connection, "SELECT * FROM `szakvezeto`");
    $temavezeto_tabla = mysqli_query($db_connection, "SELECT * FROM `temavezeto`");

    $feladat_tomb = array();
    $hallgato_tomb = array();
    $reszfeladat_tomb = array();
    $szak_tomb = array();
    $szakvezeto_tomb = array();
    $temavezeto_tomb = array();

    while($row = mysqli_fetch_array($feladat_tabla)){
        $feladat_tomb[]= $row;
    }
    while($row = mysqli_fetch_array($hallgato_tabla)){
        $hallgato_tomb[]= $row;
    }
    while($row = mysqli_fetch_array($reszfeladat_tabla)){
        $reszfeladat_tomb[]= $row;
    }
    while($row = mysqli_fetch_array($szak_tabla)){
        $szak_tomb[]= $row;
    }
    while($row = mysqli_fetch_array($szakvezeto_tabla)){
        $szakvezeto_tomb[]= $row;
    }
    while($row = mysqli_fetch_array($temavezeto_tabla)){
        $temavezeto_tomb[]= $row;
    }
    $kiir_tartalom ="<ul>";

    foreach($feladat_tomb as $id=>$item){
        $kiir_tartalom .= "<li>Hallgato id: ".$item['hallgato_id']."</li>";
        $kiir_tartalom .="<li>Lírás: ".$item['leiras']."</li>";
        $kiir_tartalom .= "<li>Név: ".$item['nev']."</li>";
        $kiir_tartalom .= "<li>Témavezető id: ".$item['temavezeto_id']."</li>";
    }
    $kiir_tartalom .="</ul>";
    echo $kiir_tartalom;