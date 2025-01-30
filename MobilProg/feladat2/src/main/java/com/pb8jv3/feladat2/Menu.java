package com.pb8jv3.feladat2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    TextView nevjelszo;
    EditText p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        nevjelszo = findViewById(R.id.nevjelszo);
        p = findViewById(R.id.page);
    }

    public void b1(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
    public void b2(View v) { startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 1);}
    public void b3(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
    }
    public void b4(View v){
        startActivityForResult(new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI), 1);
    }
    public void b5(View v){
        Intent web = new Intent();
        web.setAction(Intent.ACTION_VIEW);
        web.setData(Uri.parse(p.getText().toString()));
        startActivity(web);
    }

    public void azonositas(View v){
        Intent i = new Intent();
        i.setClass(this, Azonositas.class);
        startActivityForResult(i, 15);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 15) {
            if(data != null) {
                nevjelszo.setText(data.getStringExtra("nevk") + " | " + data.getStringExtra("jelszok"));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("mentes", nevjelszo.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        nevjelszo.setText(savedInstanceState.getString("mentes"));
    }
}