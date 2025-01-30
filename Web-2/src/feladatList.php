<?php
require('dbConnect.php');
$db = OpenDB();
$page = "<ul><li>------------------------------------------------------------</li>";
$feladatok_result = mysqli_query($db, "SELECT * FROM `feladat`");
if (mysqli_num_rows($feladatok_result) > 0) {
    while ($row = mysqli_fetch_assoc($feladatok_result)) {
        $hallgato_id = $row['hallgato_id'];
        $temavezeto_id = $row['temavezeto_id'];

        $hallgato_result = mysqli_query($db, "SELECT * FROM `hallgato` WHERE `id` = '$hallgato_id'");
        $hallgato_record = mysqli_fetch_assoc($hallgato_result);

        $temavezeto_result = mysqli_query($db, "SELECT * FROM `temavezeto` WHERE `id` = '$temavezeto_id'");
        $temavezeto_record = mysqli_fetch_assoc($temavezeto_result);

        $page .= "<li>Feladat cime: " . $row['nev'] . "</li>";
        $page .= "<li>Hallgato neve: " . $hallgato_record['nev'] . "</li>";
        $page .= "<li>Temavezeto neve: " . $temavezeto_record['nev'] . "</li>";
        $page .= "<li>------------------------------------------------------------</li>";
    }
    $page .= "</ul>";
    echo $page;
} else {
    echo "Nincs feladat az adatbazisban";
}
