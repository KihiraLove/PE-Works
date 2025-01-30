package com.example.gyak10_kedd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText nev, kor;
    TextView inditasokTv;
    Integer db=1;
    SharedPreferences sp;
    String datum;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nev = findViewById(R.id.neveEt);
        kor = findViewById(R.id.koraEt);
        inditasokTv = findViewById(R.id.inditasokTv);
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sp = getSharedPreferences("alapadatok",MODE_PRIVATE);
        Boolean elso = sp.getBoolean("elsokulcs",true);
        if (elso)
            Toast.makeText(this, "Első indulás! \n " +
                    "Add meg a neved és korod!", Toast.LENGTH_SHORT).show();
        else {
            String neve = sp.getString("nevkulcs","");
            String kora = sp.getString("korkulcs","");
            db = sp.getInt("dbkulcs",1);
            nev.setText(neve);
            nev.setEnabled(false);
            kor.setText(kora);
            kor.setEnabled(false);
            inditasokTv.setText(db+"");
            Toast.makeText(this, "Szia "+neve+"!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        db++;
        sp = getSharedPreferences("alapadatok",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nevkulcs",nev.getText().toString());
        editor.putString("korkulcs",kor.getText().toString());
        editor.putBoolean("elsokulcs",false);
        editor.putInt("dbkulcs",db);
        editor.commit();
        //alapadatok: Device File Explorer
        // -> data -> data -> csomagnév -> shared_prefs

        //InternalStorage: Device File Explorer
        //-> data -> data -> csomagnév -> files -> hasznalat
        datum = sdf.format(new Date());
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("hasznalat",MODE_APPEND);
            datum += "\n";
            fileOutputStream.write(datum.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //External Storage
        File file = getExternalFilesDir(null);
        String filename = file.getAbsolutePath()+"/inditasokszama.txt";
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filename);
            outputStream.write(Integer.toString(db).getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}