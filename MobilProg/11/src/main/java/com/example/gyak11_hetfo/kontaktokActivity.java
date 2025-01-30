package com.example.gyak11_hetfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class kontaktokActivity extends AppCompatActivity {

    ListView lista;
    ArrayList<String> kontaktok;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontaktok);
        lista = findViewById(R.id.lista);

        //van-e engedély?
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
            kontaktMutat();
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    100
                    );
        }

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE},
                200);

        //lista és adapter inicializálás
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                kontaktok);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(oicListener);
    }

    AdapterView.OnItemClickListener oicListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String valasztott = adapterView.getItemAtPosition(i).toString();
            //Log.d("proba",valasztott);
            int holvan = valasztott.lastIndexOf(":");
            String telszam = valasztott.substring(holvan + 1);
            AlertDialog.Builder adb = new AlertDialog.Builder(
                    kontaktokActivity.this);
            adb.setMessage("Telefonálsz vagy SMS-t küldenél?");
            adb.setTitle("Válassz!");
            adb.setIcon(android.R.drawable.ic_menu_compass);
            adb.setNeutralButton("SMS küldés", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto: "+telszam));
                    intent.putExtra("sms_body","Szia!");
                    startActivity(intent);
                }
            });
            adb.setPositiveButton("Hívásindítás",
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel: "+telszam));
                    startActivity(intent);
                }
            });
            adb.show();
        }
    };

    public void kontaktMutat(){
        kontaktok = new ArrayList<>();
        Cursor c;
        c = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                );
        while (c.moveToNext()){
            int index1 = c.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int index2 = c.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER);
            String kontaktNev = c.getString(index1);
            String telSzam = c.getString(index2);
            kontaktok.add("Név: " + kontaktNev + "\n" + "Telszám: " + telSzam);
        }
        c.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 100: if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        //Felhasználó megadta az engedélyt a névjegyzékre
                        kontaktMutat();
                    else //felhasználó elutasította az engedélyt
                Toast.makeText(this, "Adj engedélyt a névjegyzékhez",
                        Toast.LENGTH_SHORT).show();
                    break;
            case 200: if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                Toast.makeText(this, "Adj engedélyt a telefonáláshoz",
                        Toast.LENGTH_SHORT).show();
        }
    }
}