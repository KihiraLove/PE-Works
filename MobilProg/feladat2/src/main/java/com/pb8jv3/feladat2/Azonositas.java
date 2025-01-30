package com.pb8jv3.feladat2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Azonositas extends AppCompatActivity {

    EditText nev, jelszo;
    Button bsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azonositas);
        nev = findViewById(R.id.nev);
        jelszo = findViewById(R.id.jelszo);
        bsend = findViewById(R.id.bsend);

        bsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Azonositas.this, Menu.class);

                i.putExtra("nevk", nev.getText().toString());
                i.putExtra("jelszok", jelszo.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}