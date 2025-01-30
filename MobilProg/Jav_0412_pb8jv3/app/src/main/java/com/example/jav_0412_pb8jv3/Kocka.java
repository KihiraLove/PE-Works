package com.example.jav_0412_pb8jv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Kocka extends AppCompatActivity {

    EditText fennmarado;
    EditText tet;

    TextView random1;
    TextView random2;

    Button dobas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kocka);

        fennmarado = findViewById(R.id.fennmarado);
        tet = findViewById(R.id.tet);

        random1 = findViewById(R.id.random1);
        random2 = findViewById(R.id.random2);

        dobas = findViewById(R.id.dobas);
    }

    public void onClickDobas(View v) {
        int r1 = (int) ((Math.random() * (6 - 1)) + 1);
        int r2 = (int) ((Math.random() * (6 - 1)) + 1);

        Log.i("INFO", "r1: " + r1);
        Log.i("INFO", "r2: " + r2);
        if (tet.getText().toString().length() == 0){
            Toast.makeText(this, "Tét mező üres", Toast.LENGTH_SHORT).show();
            return;
        }
        if(r1 == r2){
            Toast.makeText(this, "!!! Nyert - Dupla !!!", Toast.LENGTH_SHORT).show();
            Log.i("INFO", "Fennmarado: " + fennmarado.getText().toString());
            Log.i("INFO", "Tet: " + tet.getText().toString());
            Log.i("INFO", "F int: " + Integer.parseInt(fennmarado.getText().toString()));
            Log.i("INFO", "T int: " + Integer.parseInt(tet.getText().toString()));
            int fm = Integer.parseInt(fennmarado.getText().toString());
            int t = Integer.parseInt(tet.getText().toString());
            int s = fm + t;
            fennmarado.setText("" + s);
        } else {
            Toast.makeText(this, "Vesztett", Toast.LENGTH_SHORT).show();
            int fm = Integer.parseInt(fennmarado.getText().toString());
            int t = Integer.parseInt(tet.getText().toString());
            int s = fm - t;
            fennmarado.setText("" + s);
        }
        random1.setText("" + r1);
        random2.setText("" + r2);
    }


    @Override
    protected void onPause(){
        super.onPause();
        Date currentTime = Calendar.getInstance().getTime();
        SharedPreferences sharedPref = getSharedPreferences("kockaPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("fennmarado", Integer.parseInt(fennmarado.getText().toString()));
        editor.putString("ido", currentTime.toString());
        editor.apply();
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("kockaPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        fennmarado.setText(sharedPref.getInt("fennmarado", 1000) + "");
        Toast.makeText(this, "Utolso játék: " + sharedPref.getString("ido", "nincs adat"), Toast.LENGTH_SHORT).show();
        editor.remove("fennmarado");
        editor.remove("ido");
    }
}