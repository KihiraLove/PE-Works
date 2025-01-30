package com.example.gyak4_2022;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class menuActivity extends AppCompatActivity {

    TextView azonositasTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        azonositasTv = findViewById(R.id.azonositasTV);

        //1. megoldás
        /*
        Bundle extrak = getIntent().getExtras();
        if (extrak != null) {
            azonositasTv.setText(extrak.getString("nevk") + " | " + extrak.getString("jelszok"));
        }
         */
    }

    public void azonKatt(View v){
        Intent i = new Intent();
        i.setClass(this, azonositasActivity.class); //honnan, hova
        //startActivity(i); //1. megoldás
        startActivityForResult(i, 15); //2. megoldás
    }

    //adat lekérés - 2. megoldás esetén
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 15){ //azonositasActivity-ről tértünk vissza
            if (data !=  null){
                azonositasTv.setText(data.getStringExtra("nevk") + " | " + data.getStringExtra("jelszok"));
            }
        }
    }

    //adatmentés forgatás esetére
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mentes",azonositasTv.getText().toString());
    }

    //visszatöltés
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        azonositasTv.setText(savedInstanceState.getString("mentes"));
    }
}