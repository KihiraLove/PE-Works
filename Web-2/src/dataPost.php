<?php
require('dbConnect.php');
$db = OpenDB();
echo "Kapcsolat az adatbazishoz nyitva <br>";
if (isset($_POST['hallgato_nev'])
    && isset($_POST['neptunkod'])
    && isset($_POST['szak'])
    && isset($_POST['szakvezeto'])
    && isset($_POST['feladat_cim'])
    && isset($_POST['feladat_leiras'])
    && isset($_POST['temavezeto'])
    && count($_POST) >= 7) {

    $hallgato_nev = $_POST['hallgato_nev'];
    $neptunkod = $_POST['neptunkod'];
    $szak = $_POST['szak'];
    $szakvezeto = $_POST['szakvezeto'];
    $feladat_nev = $_POST['feladat_cim'];
    $feladat_leiras = $_POST['feladat_leiras'];
    $temavezeto = $_POST['temavezeto'];

    $reszfeladatok = array();
    $counter = 0;
    for ($i = 7; $i < count($_POST); $i++) {
        $reszfeladatok[$counter] = $_POST['reszfeladat' . ($i - 6)];
        $counter++;
    }

    $hallgato_result = mysqli_query($db, "SELECT * FROM `hallgato` WHERE `neptun_kod` = '$neptunkod'");
    $szak_result = mysqli_query($db, "SELECT * FROM `szak` WHERE `nev` = '$szak'");
    $temavezeto_result = mysqli_query($db, "SELECT * FROM `temavezeto` WHERE `nev` = '$temavezeto'");

    $hallgato_record = NULL;
    $szak_record = NULL;
    $temavezeto_record = NULL;

    if (mysqli_num_rows($szak_result) > 0) {
        $szak_record = mysqli_fetch_assoc($szak_result);
        echo "szakvetezo es szak letezik az adatbazisban <br>";
    } else {
        mysqli_query($db, "INSERT INTO `szakvezeto` (`nev`) VALUES ('$szakvezeto')");
        $szakvezeto_result = mysqli_query($db, "SELECT * FROM `szakvezeto` WHERE `nev` = '$szakvezeto'");
        $szakvezeto_record = mysqli_fetch_assoc($szakvezeto_result);
        $szakvezeto_id = $szakvezeto_record['id'];
        mysqli_query($db, "INSERT INTO `szak` (`nev`, `szakvezeto_id`) VALUES ('$szak', '$szakvezeto_id')");
        $szak_result = mysqli_query($db, "SELECT * FROM `szak` WHERE `nev` = '$szak'");
        $szak_record = mysqli_fetch_assoc($szak_result);
        echo "szakvetezo es szak hozzaadva az adatbazishoz <br>";
    }

    if (mysqli_num_rows($temavezeto_result) > 0) {
        $temavezeto_record = mysqli_fetch_assoc($temavezeto_result);
        echo "temavezeto letezik az adatbazisban <br>";
    } else {
        mysqli_query($db, "INSERT INTO `temavezeto` (`nev`) VALUES ('$temavezeto')");
        $temavezeto_result = mysqli_query($db, "SELECT * FROM `temavezeto` WHERE `nev` = '$temavezeto'");
        $temavezeto_record = mysqli_fetch_assoc($temavezeto_result);
        echo "temavezeto hozzaadva az adatbazishoz <br>";
    }

    if (mysqli_num_rows($hallgato_result) > 0) {
        $hallgato_record = mysqli_fetch_assoc($hallgato_result);
        echo "hallgato letezik az adatbazisban <br>";
    } else {
        $szak_id = $szak_record['id'];
        mysqli_query($db, "INSERT INTO `hallgato` (`nev`, `neptun_kod`, `szak_id`) VALUES ('$hallgato_nev', '$neptunkod', '$szak_id')");
        $hallgato_result = mysqli_query($db, "SELECT * FROM `hallgato` WHERE `neptun_kod` = '$neptunkod'");
        $hallgato_record = mysqli_fetch_assoc($hallgato_result);
        echo "hallgato hozzaadva az adatbazishoz <br>";
    }

    $hallgato_id = $hallgato_record['id'];
    $temavezeto_id = $temavezeto_record['id'];

    mysqli_query($db, "INSERT INTO `feladat` (`nev`, `leiras`, `hallgato_id`, `temavezeto_id`) VALUES ('$feladat_nev', '$feladat_leiras', '$hallgato_id', '$temavezeto_id')");
    $feladat_result = mysqli_query($db, "SELECT * FROM `feladat` WHERE `nev` = '$feladat_nev' AND `leiras` = '$feladat_leiras'");
    $feladat_record = mysqli_fetch_assoc($feladat_result);
    $feladat_id = $feladat_record['id'];
    echo "Feladat hozzáadva az adatbazishoz <br>";

    for ($i = 0; $i < count($reszfeladatok); $i++) {
        mysqli_query($db, "INSERT INTO `reszfeladat` (`feladat_id`, `leiras`) VALUES ('$feladat_id', '$reszfeladatok[$i]')");
        echo "Reszfeladat " . $i . " hozzaadva az adatbazishoz <br>";
    }
    CloseDB($db);
}
