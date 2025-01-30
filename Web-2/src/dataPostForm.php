<html>
<head>
    <script>
        var reszfeladat_n;

        function spawn_reszfeladatok() {
            reszfeladat_n = document.getElementById("reszfeladatok_n").value;
            var add = '';
            var i;

            for (i = 0; i < reszfeladat_n; i++) {
                add += 'Reszfeladat ' + (i + 1) + ': <input type="text" id="reszfeladat' + (i + 1) + '" name="reszfeladat' + (i + 1) + '"><br>'
            }

            document.getElementById("reszfeladatok").innerHTML = add;
        }
    </script>
</head>
<body>
<form action="dataPost.php" method="POST">
    Hallgato neve: <input type="text" id="hallgato_nev" name="hallgato_nev"><br>
    Neptun kod: <input type="text" id="neptunkod" name="neptunkod"><br>
    Szak: <input type="text" id="szak" name="szak"><br>
    Szakvezeto: <input type="text" id="szakvezeto" name="szakvezeto"><br>
    Feladat cim: <input type="text" id="feladat" name="feladat_cim"><br>
    Feladat leiras: <input type="text" id="feladat" name="feladat_leiras"><br>
    Temavezeto: <input type="text" id="temavezeto" name="temavezeto"><br>
    <div id="reszfeladatok"></div>
    <button type="submit">Mentés</button>
</form>
Reszfeladatok szama: <input type="number" id="reszfeladatok_n" name="reszfeladatok_n"<br>
<button id="reszf_sp" onclick="spawn_reszfeladatok()">Reszfeladatok Hozzaadasa</button>
</body>
</html>