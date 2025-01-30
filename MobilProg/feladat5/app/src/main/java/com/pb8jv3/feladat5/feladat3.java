package com.pb8jv3.feladat5;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class feladat3 extends AppCompatActivity {

    TextView sensorTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feladat3);

        sensorTv = findViewById(R.id.sensorTv);

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s:sensors){
            if(s.getVersion() >= 2){
                sensorTv.append(s.getName()+"\n");
            }
        }
    }


}