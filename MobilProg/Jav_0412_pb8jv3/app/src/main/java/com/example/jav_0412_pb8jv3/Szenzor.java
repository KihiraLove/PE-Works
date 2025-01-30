package com.example.jav_0412_pb8jv3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Szenzor extends AppCompatActivity {

    TextView ltv;
    Switch lswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szenzor);

        ltv = findViewById(R.id.ltv);
        lswitch = findViewById(R.id.lswitch);

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor lightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor != null){
            mySensorManager.registerListener(
                    lightSensorListener,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "Nincs light sensor", Toast.LENGTH_SHORT).show();
        }
    }

    private final SensorEventListener lightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //auto generated
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                if(event.values[0] > 100){
                    ltv.setBackgroundColor(Color.WHITE);
                    ltv.setText(event.values[0] + "lux - villany le");
                    lswitch.setChecked(false);
                } else if (event.values[0] > 50) {
                    ltv.setBackgroundColor(Color.GRAY);
                    ltv.setText(event.values[0] + "lux - villany/1");
                    lswitch.setChecked(true);
                } else {
                    ltv.setBackgroundColor(Color.BLUE);
                    ltv.setText(event.values[0] + "lux - villany/2");
                    lswitch.setChecked(true);
                }
            }
        }

    };
}