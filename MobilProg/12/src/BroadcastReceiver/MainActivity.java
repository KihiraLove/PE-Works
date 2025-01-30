package com.example.broadcastreceiver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    IntentFilter intfilt = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder adb = new AlertDialog.Builder(context);
            adb.setMessage("Töltőre csatlakoztatva");
            adb.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(br, intfilt);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(br);
    }
}