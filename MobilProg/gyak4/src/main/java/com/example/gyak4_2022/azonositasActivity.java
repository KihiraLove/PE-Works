package com.example.gyak4_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.versionedparcelable.VersionedParcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class azonositasActivity extends AppCompatActivity {

    EditText nev, jelszo;
    Button kuldes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azonositas);
        nev = findViewById(R.id.nevEt);
        jelszo =findViewById(R.id.jelszoEt);
        kuldes = findViewById(R.id.kuldesBtn);
        kuldes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(azonositasActivity.this, menuActivity.class);
                i.putExtra("nevk", nev.getText().toString());
                i.putExtra("jelszok", jelszo.getText().toString());
                //startActivity(i); //1. megoldás
                setResult(RESULT_OK, i); //visszatérési mód - 2. megoldás
                finish(); //bezáródik, és lefut a menuActivity onActivityResult metódusa
            }
        });
    }
}