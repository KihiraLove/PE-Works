package com.example.telephonytutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_SMS = 2;

    private EditText numberInput;
    private EditText messageInput;
    private ImageView imageCall;
    private ImageView imageSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = (EditText) findViewById(R.id.phoneNumber_ET);
        messageInput = (EditText) findViewById(R.id.smsMessage_ET);

        imageCall = (ImageView) findViewById(R.id.phone_image);
        imageSMS = (ImageView) findViewById(R.id.sms_image);

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });

        imageSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {sendSMS();}
        });

    }

    private void makePhoneCall(){
        String number = numberInput.getText().toString();

        if(number.trim().length() > 0){
            if(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(this, "Enter a phone number!", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendSMS(){
        String number = numberInput.getText().toString();
        String message = messageInput.getText().toString();

        if(number.trim().length() > 0 && message.length() > 0){
            if(ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.SEND_SMS}, REQUEST_SMS);
            } else {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, message, null, null);
                Toast.makeText(this, "SMS sent...", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter phone number and/or SMS message!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        } else if(requestCode == REQUEST_SMS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendSMS();
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}