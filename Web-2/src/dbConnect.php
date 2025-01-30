<?php

function OpenDB()
{
    $dbhost = "127.0.0.1";
    $dbuser = "root";
    $dbpass = "";
    $dbname = "beadando";

    $connection = new mysqli($dbhost, $dbuser, $dbpass, $dbname) or die("Connect failed: %s\n". $connection -> error);

    return $connection;
}

function CloseDB($connection)
{
    $connection -> close();
}

?>