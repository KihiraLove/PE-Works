package com.example.relativactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Switch s1;
    SeekBar sb;
    TextView sbErtek, mintaszoveg, felirat;
    ImageView kep;
    RadioGroup rg;
    RadioButton rbKutya, rbMacska;
    Button datumBtn;
    EditText datumEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //referenciák
        s1 = findViewById(R.id.switch1);
        sb = findViewById(R.id.seekBar);
        sbErtek = findViewById(R.id.seekBarTv);
        mintaszoveg = findViewById(R.id.mintaszoveg);
        kep = findViewById(R.id.kep);
        rbKutya = findViewById(R.id.rbKutya);
        rbMacska = findViewById(R.id.rbMacska);
        rg = findViewById(R.id.rg1);
        datumBtn = findViewById(R.id.datumBtn);
        datumEt = findViewById(R.id.datumEt);
        felirat = findViewById(R.id.felirat);
        felirat.setText(R.string.vezerlok);
        Button kepek = findViewById(R.id.kepek);
        kepek.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,
                kepekActivity.class)));

        //animáció
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.szoveganimacio);
        felirat.startAnimation(animation);

        //listenerek
        //switch
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton,
                                         boolean b) {
                if (compoundButton.isChecked()){
                    s1.setText("Bekapcsolva");
                    s1.setBackgroundColor(Color.rgb(255,255,0));
                }
                else {
                    s1.setText("Kikapcsolva");
                    s1.setBackgroundColor(Color.rgb(200,200,200));
                }
            }
        });

        //seekBar
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sbErtek.setText(i + "sp");
                mintaszoveg.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sbErtek.setBackgroundColor(Color.RED);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //sbErtek.setBackgroundColor(Color.GREEN);
                sbErtek.setBackgroundColor(Color.parseColor("#00FF00"));
            }
        });

        //RadioGroup és RadioButton
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == rbKutya.getId()){
                    kep.setBackgroundResource(R.drawable.kutya);
                }
                else kep.setBackgroundResource(R.drawable.cica);
            }
        });

        //dátum választás
        datumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int ev = c.get(Calendar.YEAR);
                int ho = c.get(Calendar.MONTH);
                int nap = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                datumEt.setText(i + "." + i1 + "." + i2);
                            }
                        },
                        ev, ho, nap
                );
                dpd.show();
            }
        });

    }
}