package com.pb8jv3.testomegindex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.core.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText weightIn, ageIn, heightIn;
    ImageView incAge, decAge, incWeight, decWeight;
    SeekBar heightSeekBar;
    Button calculate;
    RelativeLayout maleLayout, femaleLayout;

    int defaultHeight = 193;
    int defaultWeight = 97;
    int defaultAge = 26;
    String heightStr = Integer.toString(defaultHeight);
    String defWStr = Integer.toString(defaultWeight);
    String defAgeStr = Integer.toString(defaultAge);
    String defaultGender = "empty";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        ageIn = findViewById(R.id.current_age);
        weightIn = findViewById(R.id.current_weight);
        heightIn = findViewById(R.id.current_height);
        incAge = findViewById(R.id.inc_age);
        decAge = findViewById(R.id.dec_age);
        incWeight = findViewById(R.id.inc_weight);
        decWeight = findViewById(R.id.dec_weight);
        calculate = findViewById(R.id.tti_calc);
        heightSeekBar = findViewById(R.id.height_seekbar);
        maleLayout = findViewById(R.id.male);
        femaleLayout = findViewById(R.id.female);

        maleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalefocus));
                femaleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemalenotfocus));
                defaultGender = "Male";
            }
        });


        femaleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femaleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalefocus));
                maleLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemalenotfocus));
                defaultGender = "Female";
            }
        });

        heightSeekBar.setMax(300);
        heightSeekBar.setProgress(193);
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                MainActivity.this.defaultHeight =progress;
                heightStr = String.valueOf(MainActivity.this.defaultHeight);
                heightIn.setText(heightStr);
                heightIn.setSelection(heightStr.length());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        heightIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String data = heightIn.getText().toString();
                if(data.length() == 0)
                    heightStr = "0";
            }

            @Override
            public void afterTextChanged(Editable editable) {

                try{
                    int progress = Integer.parseInt(editable.toString());
                    heightSeekBar.setProgress(progress);
                    heightStr = String.valueOf(progress);
                }catch (Exception e){}
            }
        });


        ageIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String data = ageIn.getText().toString();
                if(data.length() == 0)
                    defaultAge = 0;
            }

            @Override
            public void afterTextChanged(Editable editable) {

                try{
                    int p1 = Integer.parseInt(editable.toString());
                    defAgeStr = String.valueOf(p1);
                    defaultAge = p1;

                }catch (Exception e){}
            }
        });


        weightIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String data= weightIn.getText().toString();
                if(data.length() == 0)
                    defaultWeight = 0;
            }

            @Override
            public void afterTextChanged(Editable editable) {

                try{
                    int p2 = Integer.parseInt(editable.toString());
                    defaultWeight = p2;
                    defWStr = String.valueOf(p2);


                }catch (Exception e){}
            }
        });
        incWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultWeight = defaultWeight + 1;
                defWStr = String.valueOf(defaultWeight);
                weightIn.setText(defWStr);
            }
        });

        incAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultAge = defaultAge + 1;
                defAgeStr = String.valueOf(defaultAge);
                ageIn.setText(defAgeStr);
            }
        });


        decAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultAge = defaultAge - 1;
                defAgeStr = String.valueOf(defaultAge);
                ageIn.setText(defAgeStr);
            }
        });


        decWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                defaultWeight = defaultWeight - 1;
                defWStr = String.valueOf(defaultWeight);
                weightIn.setText(defWStr);
            }
        });



        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(defaultGender.equals("empty"))
                {
                    Toast.makeText(getApplicationContext(),"Válassz nemet!", Toast.LENGTH_SHORT).show();
                }
                else if(heightStr.equals("0") || heightStr.equals("") )
                {
                    Toast.makeText(getApplicationContext(),"Add meg a magasságod", Toast.LENGTH_SHORT).show();
                }
                else if(defaultAge ==0 || defaultAge <0 || defAgeStr.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Adj meg valós életkort", Toast.LENGTH_SHORT).show();
                }

                else if(defaultWeight ==0|| defaultWeight <0 || defWStr.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Add meg a tömeged", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, TTIActivity.class);
                    intent.putExtra("gender", defaultGender);
                    intent.putExtra("height", heightStr);
                    intent.putExtra("weight", defWStr);
                    intent.putExtra("age", defAgeStr);
                    startActivity(intent);
                }
            }
        });
    }
}