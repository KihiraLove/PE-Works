package com.example.gyakorlat4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Switch switch1;
    private SeekBar seekBar;
    private TextView seekBarTv, mintaszoveg;
    private RadioGroup rg1;
    private RadioButton kutya, macska;
    private ImageView kep;
    private Button datumBtn;
    private EditText datumEt;
    private Button kepekBtn;

    public void kepekKattintas(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void datumKattintas(){
        Calendar c = Calendar.getInstance();
        int ev = c.get(Calendar.YEAR);
        int honap = c.get(Calendar.MONTH);
        int nap = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ev, int honap, int nap) {
                        datumEt.setText(ev + "." + (honap + 1) + "." + nap + ".");
                    }
                },
                ev,honap,nap
        );
        dpd.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setBackgroundColor(Color.BLUE);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarTv = (TextView) findViewById(R.id.seekBarTv);
        mintaszoveg = (TextView) findViewById(R.id.mintaszoveg);

        rg1 = (RadioGroup) findViewById(R.id.rg1);
        kutya = (RadioButton) findViewById(R.id.rbKutya);
        macska = (RadioButton) findViewById(R.id.rbMacska);
        kep = (ImageView) findViewById(R.id.kep);

        datumBtn = (Button) findViewById(R.id.datumBtn);
        datumEt = (EditText) findViewById(R.id.datumEt);

        kepekBtn = (Button) findViewById(R.id.kepek);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    switch1.setText("Bekapcsolva");
                    switch1.setBackgroundColor(Color.RED);
                }else{
                    switch1.setText("Alapállapotban");
                    switch1.setBackgroundColor(Color.BLUE);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarTv.setText(i + "sp");
                mintaszoveg.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setBackgroundColor(Color.BLUE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.setBackgroundColor(Color.GREEN);
            }
        });

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rg1.getCheckedRadioButtonId() == kutya.getId()){
                    kep.setBackgroundResource(R.drawable.kutya);
                }else if(rg1.getCheckedRadioButtonId() == macska.getId()){
                    kep.setBackgroundResource(R.drawable.macska);
                }
            }
        });

        datumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datumKattintas();
            }
        });

        kepekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kepekKattintas();
            }
        });
    }
}