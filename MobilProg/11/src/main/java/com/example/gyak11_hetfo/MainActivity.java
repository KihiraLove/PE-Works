package com.example.gyak11_hetfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nevjegyzekMutat(View v){
        startActivity(new Intent(this,
                kontaktokActivity.class));
    }
    public void szuresMutat(View v){
        startActivity(new Intent(this,
                kontaktokActivity.class));
    }
    public void smsMutat(View v){
        startActivity(new Intent(this,
                smsActivity.class));
    }
}