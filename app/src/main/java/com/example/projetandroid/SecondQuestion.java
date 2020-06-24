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

public class SecondQuestion extends AppCompatActivity {

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
    List<String> rep2;
    Questions question2;
    String theme;
    int resultat;
    int selectedRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);
        View background = findViewById(R.id.r2);

        question = findViewById(R.id.txtQuestion2);
        rdGroup = findViewById(R.id.rdGroup2);
        rdReponse1 = findViewById(R.id.reponse1Question2);
        rdReponse2 = findViewById(R.id.reponse2Question2);
        rdReponse3 = findViewById(R.id.reponse3Question2);
        next = findViewById(R.id.btnNext2);
        resultat = -1;

        i = getIntent();
        extras = i.getExtras();
        theme = extras.getString("theme");
        score = extras.getInt("score");

        rep2 = new ArrayList<>();

        try{
            switch (theme){
                case "Astronomie":
                    BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.astronomy);
                    background.setBackground(bg);
                    rep2.add("3.500 - 5.900 °C");
                    rep2.add("6.000 - 7.000 °C");
                    rep2.add("8.000 - 10.000");
                    question2 = new Questions("Quelle est la température du Soleil ?",rep2,1);
                    break;
                case "Sport":
                    BitmapDrawable bg3 = (BitmapDrawable)getResources().getDrawable(R.drawable.sport);
                    background.setBackground(bg3);
                    rep2.add("Andrés Iniesta");
                    rep2.add("Daniel Alves");
                    rep2.add("Gérard Piqué");
                    question2 = new Questions("Qui est le joueur le plus titrés de l'histoire du Football ?",rep2,2);
                    break;
                case "Technologie":
                    BitmapDrawable bg4 = (BitmapDrawable)getResources().getDrawable(R.drawable.technology);
                    background.setBackground(bg4);
                    rep2.add("James Gosling");
                    rep2.add("Mark Gosling");
                    rep2.add("Linus Gosling");
                    question2 = new Questions("Qui est considéré comme le créateur de java?",rep2,1);
                    break;
                case "Animaux":
                    BitmapDrawable bg2 = (BitmapDrawable)getResources().getDrawable(R.drawable.animals2);
                    background.setBackground(bg2);
                    rep2.add("Black marlin");
                    rep2.add("Sailfish");
                    rep2.add("Swordfish");
                    question2 = new Questions("Quel est l'animal le plus rapide du monde dans l'eau?",rep2,1);
                    break;
                default:
                    break;
            }

            question.setText(question2.question);
            rdReponse1.setText(question2.reponses.get(0));
            rdReponse2.setText(question2.reponses.get(1));
            rdReponse3.setText(question2.reponses.get(2));

        }catch (Exception ex){
            Toast.makeText(SecondQuestion.this,"Error : "+ex.getMessage().toString(), Toast.LENGTH_LONG ).show();
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (resultat == -1) {
                        Toast.makeText(SecondQuestion.this, "Vueillez Choisir Une Question", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(SecondQuestion.this, ThirdQuestion.class);
                        extras.clear();

                        extras.putString("theme", theme);
                        score += resultat;
                        extras.putInt("score", score);
                        intent.putExtras(extras);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(SecondQuestion.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addScore(View v){

        try {
            selectedRep = rdGroup.getCheckedRadioButtonId();
            rdReponseUser = findViewById(selectedRep);
        }catch (Exception ex){
            Toast.makeText(SecondQuestion.this, "Error : "+ex.getMessage(), Toast.LENGTH_LONG);
        }
        if (selectedRep != -1) {
            resultat = 0;
            if (rdReponseUser.getText().toString().equals(question2.reponses.get(question2.positionTrue - 1))) {
                resultat = 5;
            }
        }
    }
}