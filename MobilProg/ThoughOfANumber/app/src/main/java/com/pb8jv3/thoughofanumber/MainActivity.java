package com.pb8jv3.thoughofanumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView inputField;
    private TextView outputField;
    int num;
    String guesses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Az alkamazás használatra kész", Toast.LENGTH_SHORT).show();
        inputField = findViewById(R.id.inputField);
        outputField = findViewById(R.id.outputField);
        Random rand = new Random();
        num = rand.nextInt(1000);
        guesses = "";
    }

    public void guess(View view){
        String gtxt = inputField.getText().toString();
        if(gtxt.length() == 0) {
            Toast.makeText(this, "Üres mező", Toast.LENGTH_LONG).show();
        } else {
            int g = Integer.parseInt(gtxt);
            guesses += "\n" + g;
            if(g > num) {
                outputField.setText("Kisebb" + guesses);
            } else if (g < num){
                outputField.setText("Nagyobb" + guesses);
            } else {
                outputField.setText("Eltaláltad");
            }
        }
    }
}