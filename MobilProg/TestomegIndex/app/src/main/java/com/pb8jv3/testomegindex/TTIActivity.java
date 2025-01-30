package com.pb8jv3.testomegindex;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TTIActivity extends AppCompatActivity {
    TextView ttiDisplay, ttiCategory, gender;
    Button recalculate;
    Intent intent;
    ImageView imView;
    RelativeLayout background;

    String height;
    String weight;
    float fHeight;
    float fWeight;
    float tti;
    String ttiStr;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tti);
        getSupportActionBar().setElevation(0);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Result");

        intent = getIntent();
        ttiDisplay = findViewById(R.id.tti_display);

        ttiCategory = findViewById(R.id.tti_category_display);
        recalculate = findViewById(R.id.recalculate);

        imView = findViewById(R.id.imageview);

        gender = findViewById(R.id.gender_display);
        background = findViewById(R.id.contentlayout);

        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        fHeight = Float.parseFloat(height);
        fWeight = Float.parseFloat(weight);

        fHeight = fHeight / 100;
        tti = fWeight / (fHeight * fHeight);

        ttiStr = Integer.toString((int)tti);
        System.out.println(ttiStr);

        if(tti < 16)
        {
            ttiCategory.setText("Súlyosan sovány");
            background.setBackgroundColor(Color.RED);
            imView.setImageResource(R.drawable.crosss);

        }
        else if(tti < 16.9 && tti > 16)
        {
            ttiCategory.setText("Mérsékelt sovány");
            background.setBackgroundColor(R.color.halfwarn);
            imView.setImageResource(R.drawable.warning);

        }
        else if(tti < 18.4 && tti > 17)
        {
            ttiCategory.setText("Enyén sovány");
            background.setBackgroundColor(R.color.halfwarn);
            imView.setImageResource(R.drawable.warning);

        }
        else if(tti < 24.9 && tti > 18.5 )
        {
            ttiCategory.setText("Normál testsúly");
            imView.setImageResource(R.drawable.ok);

        }
        else if(tti < 29.9 && tti > 25)
        {
            ttiCategory.setText("Kissé Túlsúlyos");
            background.setBackgroundColor(R.color.halfwarn);
            imView.setImageResource(R.drawable.warning);

        }
        else if(tti < 34.9 && tti > 30)
        {
            ttiCategory.setText("I. fokú elhízás");
            background.setBackgroundColor(R.color.halfwarn);
            imView.setImageResource(R.drawable.warning);

        }
        else
        {
            ttiCategory.setText("II. fokú elhízás");
            background.setBackgroundColor(R.color.warn);
            imView.setImageResource(R.drawable.crosss);

        }

        gender.setText(intent.getStringExtra("Nem"));
        ttiDisplay.setText(ttiStr);

        recalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}
