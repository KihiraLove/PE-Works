<?php
require('dbConnect.php');
$db = OpenDB();
$page = "<script>";
$feladatok_result = mysqli_query($db, "SELECT * FROM `feladat`");
if (mysqli_num_rows($feladatok_result) > 0) {
    for ($i = 0; $i < mysqli_num_rows($feladatok_result); $i++) {
        $page .= "
            var modal".$i." = document.getElementById('myModal".$i."');
            var btn".$i." = document.getElementById('myBtn".$i."');
            var span".$i." = document.getElementsByClassName('close".$i."')[0];
            btn".$i.".onclick = function() {
                modal".$i.".style.display = 'block';
            }
            span".$i.".onclick = function() {
                modal".$i.".style.display = 'none';
            }
            window.onclick = function(event) {
                if (event.target == modal".$i.") {
                    modal".$i.".style.display = 'none';
                }
            }
            ";
    }
    $page .= "</script><ul>";
    $i = 0;
    while ($row = mysqli_fetch_assoc($feladatok_result)) {
        $hallgato_id = $row['hallgato_id'];
        $temavezeto_id = $row['temavezeto_id'];

        $hallgato_result = mysqli_query($db, "SELECT * FROM `hallgato` WHERE `id` = '$hallgato_id'");
        $hallgato_record = mysqli_fetch_assoc($hallgato_result);

        $temavezeto_result = mysqli_query($db, "SELECT * FROM `temavezeto` WHERE `id` = '$temavezeto_id'");
        $temavezeto_record = mysqli_fetch_assoc($temavezeto_result);

        $page .= "<li>";
        $page .=
            "Feladat cime: <button id='myBtn".$i."'>" . $row['nev'] . "</button>
            <div id='myModal".$i."' class='modal' style=\"display: none;
                                                                position: fixed;
                                                                z-index: 1;left: 0;
                                                                top: 0;
                                                                width: 100%;
                                                                height: 100%;
                                                                overflow:auto;
                                                                background-color: rgb(0,0,0);\">
                <div class='modal-content' style='background-color: #fefefe;
                                                    margin: 15% auto;
                                                    padding: 20px;
                                                    border: 1px solid #888;
                                                    width: 80%;'>
                    <span class='close".$i."' style='color: #aaa;
                                                        float: right;
                                                        font-size: 28px;
                                                        font-weight: bold;'>&times;</span>
                    <p>tetx</p>
                </div>
            </div>";
        $page .= "<div>Hallgato neve: " . $hallgato_record['nev'] . "</div>";
        $page .= "<div>Temavezeto neve: " . $temavezeto_record['nev'] . "</div>";
        $page .= "</li>";
        $i++;
    }
    $page .= "</ul>";
    echo $page;
} else {
    echo "Nincs feladat az adatbazisban";
}
