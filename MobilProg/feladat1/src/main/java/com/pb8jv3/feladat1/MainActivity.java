package com.pb8jv3.feladat1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView cimTv;
    private TextView textField;
    private TextView copyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Az alkamazás használatra kész", Toast.LENGTH_SHORT).show();
        cimTv = findViewById(R.id.cimTv);
        textField = findViewById(R.id.textField);
        copyTv = findViewById(R.id.copyTv);
    }

    public void copy(View view){
        String txt = textField.getText().toString();
        if(txt.length() == 0) {
            Toast.makeText(this, "Üres mező", Toast.LENGTH_LONG).show();
        } else {
            copyTv.setText(txt);
        }
    }

    public void del(View view){
        textField.setText("");
        copyTv.setText("");
    }
}