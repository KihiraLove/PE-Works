package com.example.szenzor2022_hetfo;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
    implements SensorEventListener {

    TextView gymAdatok, fenyAdatok, harmatpont, vangym;
    ListView lista;
    SensorManager sm;
    List<Sensor> sensors;

    double t=0, m=17.62, tn=243.12, RH=0, harmatp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gymAdatok = findViewById(R.id.gymAdatok);
        fenyAdatok = findViewById(R.id.fenyAdatok);
        harmatpont = findViewById(R.id.harmatpont);
        vangym = findViewById(R.id.vangym);
        lista = findViewById(R.id.lista);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = sm.getSensorList(Sensor.TYPE_ALL);
        lista.setAdapter(new ArrayAdapter<Sensor>(this,
                android.R.layout.simple_list_item_1,
                sensors));
    }

    public void vanegyorsulasmero(View v){
        vangym.setText("Nincs");
        for (int i = 0; i < sensors.size(); i++){
            if (sensors.get(i).getType() == Sensor.TYPE_ACCELEROMETER){
                vangym.setText("Van");
                break;
            }
        }
    }

    //szenzor adatainak feldolgozása
    //1. SensorEventListener interfész implementálása -> lásd fenn: MainActivity class
    //onSensorChanged és onAccuracyChanged metódusok felülírandók!
    //2. melyik szenzort használjuk? -> onResume
    //3. adatfeldolgozás -> onSensorChanged()
    //4. leiratkozás a szenzor figyeléséről -> onPause

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,
                sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,
                sm.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,
                sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,
                sm.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gymAdatok.setText("ACC - x: " + sensorEvent.values[0] +
                    "y: " + sensorEvent.values[1] +
                    "z: " + sensorEvent.values[2]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            if (sensorEvent.values[0] > 5000)
                fenyAdatok.setText("Világos van - " + sensorEvent.values[0] + "lx");
            else fenyAdatok.setText("Sötét van - " + sensorEvent.values[0] + "lx");
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            t = sensorEvent.values[0];
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            RH = sensorEvent.values[0];
        }
        harmatp = tn * (Math.log(RH/100) + ((m*t)/(tn+t))) /
                  (m - (Math.log(RH/100) + ((m*t)/(tn+t))));
        harmatpont.setText(harmatp + "");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}


}