package com.example.gyak4_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView eredmeny, temp;
    int szam1 = 0, muvelet = 0, ans = 0;
    String szam2 = "";
    Boolean voltszam = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eredmeny = findViewById(R.id.eredmeny);
        temp = findViewById(R.id.temp);
        //System.out.println();
        Log.d("valtas", "onCreate-ben vagyok");
    }

    //melyik gombot nyomták meg?
    public void kattintasSzam(View v){
        Button b = (Button) v;
        String gombszoveg = b.getText().toString();
        temp.append(gombszoveg);
        if (voltszam)
            szam2 += gombszoveg;
    }

    //milyen műveletet választottunk?
    public void kattintasMuvelet(View v){
        switch (v.getId()){ //mi az azonosítója annak a view-nak, amire kattintottunk?
            case R.id.bC: //minden alaphelyzetbe állítása
                eredmeny.setText("");
                temp.setText("");
                szam1 = 0;
                szam2 = "";
                voltszam = false;
                ans = 0;
                break;
            case R.id.bPlusz:
                if (ans == 0) {
                    szam1 = Integer.parseInt(temp.getText().toString());
                }
                temp.append("+");
                voltszam = true;
                muvelet = 1;
                break;
            case R.id.bMinusz:
                if (ans == 0) {
                    szam1 = Integer.parseInt(temp.getText().toString());
                }
                temp.append("-");
                voltszam = true;
                muvelet = 2;
                break;
            case R.id.bSzorzas:
                if (ans == 0) {
                    szam1 = Integer.parseInt(temp.getText().toString());
                }
                temp.append("*");
                voltszam = true;
                muvelet = 3;
                break;
            case R.id.bOsztas:
                if (ans == 0) {
                    szam1 = Integer.parseInt(temp.getText().toString());
                }
                temp.append("/");
                voltszam = true;
                muvelet = 4;
                break;
            case R.id.bEgyenlo:
                temp.setText("");
                String eredmenyst = "";
                if (ans == 0){
                    if (muvelet == 1)
                        eredmenyst = Integer.toString(szam1 + Integer.parseInt(szam2));
                    else if (muvelet == 2)
                        eredmenyst = Integer.toString(szam1 - Integer.parseInt(szam2));
                    else if (muvelet == 3)
                        eredmenyst = Integer.toString(szam1 * Integer.parseInt(szam2));
                    else if (muvelet == 4)
                        eredmenyst = Integer.toString(szam1 / Integer.parseInt(szam2));
                }
                else {
                    if (muvelet == 1)
                        eredmenyst = Integer.toString(ans + Integer.parseInt(szam2));
                    else if (muvelet == 2)
                        eredmenyst = Integer.toString(ans - Integer.parseInt(szam2));
                    else if (muvelet == 3)
                        eredmenyst = Integer.toString(ans * Integer.parseInt(szam2));
                    else if (muvelet == 4)
                        eredmenyst = Integer.toString(ans / Integer.parseInt(szam2));
                }
                eredmeny.setText(eredmenyst);
                ans = Integer.parseInt(eredmenyst);
                voltszam = false;
                szam2 = "";
                break;
        }
    }

    //további életciklus callback-metódusok
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("valtas", "onStart-ban vagyok");
    }

    //Ctrl+O
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("valtas", "onResume-ban vagyok");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("valtas", "onStop-ban vagyok");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("valtas", "onRestart-ban vagyok");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("valtas", "onPause-ban vagyok");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("valtas", "onDestroy-ban vagyok");
    }
}