package com.pb8jv3.feladat5;

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

    public void b1(View v) {MainActivity.this.startActivity(new Intent(MainActivity.this, feladat1.class));}
    public void b2(View v) {MainActivity.this.startActivity(new Intent(MainActivity.this, feladat2.class));}
    public void b3(View v) {MainActivity.this.startActivity(new Intent(MainActivity.this, feladat3.class));}
    public void b4(View v) {MainActivity.this.startActivity(new Intent(MainActivity.this, feladat4.class));}
}