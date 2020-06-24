package com.example.projetandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultScore extends AppCompatActivity {

    Intent i;
    Bundle extras;
    String nameUser,type;
    int score;
    SharedPreferences sharedPreferences;
    ImageButton replay;
    TextView nom,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_score);

        nom  = findViewById(R.id.pseudoUser);

        result = findViewById(R.id.scoreResult);
        i = getIntent();

        extras = i.getExtras();

        type = extras.getString("theme");
        score = extras.getInt("score");
        sharedPreferences = getBaseContext().getSharedPreferences("Shared", MODE_PRIVATE);
        nameUser = sharedPreferences.getString("nom", null);

        nom.setText("Pseudo\n"+nameUser.toUpperCase());
        result.setText("Score : "+score);
        if (score <10){
            result.setTextColor(Color.parseColor("#FF0000"));
        }else {
            result.setTextColor(Color.parseColor("#33CC5A"));
        }
    }
}