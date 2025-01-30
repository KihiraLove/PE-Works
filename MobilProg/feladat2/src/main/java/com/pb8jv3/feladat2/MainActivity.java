package com.pb8jv3.feladat2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView resultTv;
    TextView inputTv;
    String builder;
    int res;
    char last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.resultTv);
        inputTv = findViewById(R.id.inputTv);
        builder = "";
    }

    public void click(View v){
        Button b = (Button)v;
        switch (v.getId()){
            case R.id.bplus:
            case R.id.bminus:
            case R.id.bstar:
            case R.id.bper:
            case R.id.b0:
                if(builder.length() == 0 || !Character.isDigit(builder.charAt(builder.length() - 1)))
                    break;
            case R.id.b1:
            case R.id.b2:
            case R.id.b3:
            case R.id.b4:
            case R.id.b5:
            case R.id.b6:
            case R.id.b7:
            case R.id.b8:
            case R.id.b9:
                builder += b.getText().toString();
                inputTv.setText(builder);
                break;
            case R.id.bclear:
                resultTv.setText("");
                inputTv.setText("");
                builder = "";
                break;
            case R.id.beq:
                if(builder.length() == 0 || !Character.isDigit(builder.charAt(builder.length() - 1))){
                    Toast.makeText(this, "Hibás egyenlet", Toast.LENGTH_SHORT).show();
                    break;
                }
                eval();
                resultTv.setText(res);
                break;
            default:
                break;
        }
    }

    private void eval(){
        boolean isEven = true;
        while(builder.length() != 0) {
            int l = 0;
            while (Character.isDigit(builder.charAt(l))) {
                l++;
            }
            if(l == 0)
                last = builder.charAt(l);
            else
                res = Integer.parseInt(builder.substring(0, l));
            if(builder.length() != l)
                builder = builder.substring(l + 1);

            isEven = !isEven;
        }
    }
}