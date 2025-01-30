package com.example.jav_0412_pb8jv3;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText telefon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        telefon = findViewById(R.id.telefon);
    }

    public void kocka(View v){
        startActivity(new Intent(MainActivity.this, Kocka.class));
    }

    public void egyeb(View v){
        startActivity(new Intent(MainActivity.this, Szenzor.class));
    }

    public void hivas(View v) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(telefon.getText().toString()));
        startActivity(callIntent);
    }
}