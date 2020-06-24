package com.example.projetandroid;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ThirdQuestion extends AppCompatActivity {

    Intent i;
    TextView question;

    RadioGroup rdGroup;
    RadioButton rdReponse1;
    RadioButton rdReponse2;
    RadioButton rdReponse3;
    RadioButton rdReponseUser;

    Button next;
    int score;
    Bundle extras;
    List<String> rep3;
    Questions question3;
    String theme;
    int resultat;
    int selectedRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);
        View background = findViewById(R.id.r3);

        question = findViewById(R.id.txtQuestion3);
        rdGroup = findViewById(R.id.rdGroup3);
        rdReponse1 = findViewById(R.id.reponse1Question3);
        rdReponse2 = findViewById(R.id.reponse2Question3);
        rdReponse3 = findViewById(R.id.reponse3Question3);
        next = findViewById(R.id.btnNext3);
        resultat = -1;

        i = getIntent();
        extras = i.getExtras();
        theme = extras.getString("theme");
        score = extras.getInt("score");

        rep3 = new ArrayList<>();

        try{
            switch (theme){
                case "Astronomie":
                    BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.astronomy);
                    background.setBackground(bg);
                    rep3.add("Pluto");
                    rep3.add("Jupiter");
                    rep3.add("Mercury");
                    question3 = new Questions("Quel objet a perdu son statut de planète en 2006?",rep3,1);
                    break;
                case "Sport":
                    BitmapDrawable bg3 = (BitmapDrawable)getResources().getDrawable(R.drawable.sport);
                    background.setBackground(bg3);
                    rep3.add("10");
                    rep3.add("15");
                    rep3.add("20");
                    question3 = new Questions("Combien d'obstacles sont franchis lors d'un événement de 100 mètres haies?",rep3,1);
                    break;
                case "Technologie":
                    BitmapDrawable bg4 = (BitmapDrawable)getResources().getDrawable(R.drawable.technology);
                    background.setBackground(bg4);
                    rep3.add("2007");
                    rep3.add("2008");
                    rep3.add("2009");
                    question3 = new Questions("En quelle année le premier iPhone d'Apple a-t-il été lancé?",rep3,1);
                    break;
                case "Animaux":
                    BitmapDrawable bg2 = (BitmapDrawable)getResources().getDrawable(R.drawable.animals2);
                    background.setBackground(bg2);
                    rep3.add("Ouaouaron");
                    rep3.add("Escargot");
                    rep3.add("Chien");
                    question3 = new Questions("Quel animal ne dort jamais?",rep3,1);
                    break;
                default:
                    break;
            }

            question.setText(question3.question);
            rdReponse1.setText(question3.reponses.get(0));
            rdReponse2.setText(question3.reponses.get(1));
            rdReponse3.setText(question3.reponses.get(2));

        }catch(Exception ex){
            Toast.makeText(ThirdQuestion.this,"Error : "+ex.getMessage().toString(), Toast.LENGTH_LONG ).show();
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (resultat == -1) {
                        Toast.makeText(ThirdQuestion.this, "Vueillez Choisir Une Question", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(ThirdQuestion.this, FourthQuestion.class);
                        extras.clear();

                        extras.putString("theme", theme);
                        score += resultat;
                        extras.putInt("score", score);
                        intent.putExtras(extras);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(ThirdQuestion.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addScore(View v){

        try {
            selectedRep = rdGroup.getCheckedRadioButtonId();
            rdReponseUser = findViewById(selectedRep);
        }catch (Exception ex){
            Toast.makeText(ThirdQuestion.this, "Error : "+ex.getMessage(), Toast.LENGTH_LONG);
        }
        if (selectedRep != -1) {
            resultat = 0;
            if (rdReponseUser.getText().toString().equals(question3.reponses.get(question3.positionTrue - 1))) {
                resultat = 5;
            }
        }
    }

}