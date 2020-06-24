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

public class FourthQuestion extends AppCompatActivity {

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
    List<String> rep4;
    Questions question4;
    String theme;
    int resultat;
    int selectedRep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);
        View background = findViewById(R.id.r4);

        question = findViewById(R.id.txtQuestion4);
        rdGroup = findViewById(R.id.rdGroup4);
        rdReponse1 = findViewById(R.id.reponse1Question4);
        rdReponse2 = findViewById(R.id.reponse2Question4);
        rdReponse3 = findViewById(R.id.reponse3Question4);
        next = findViewById(R.id.btnNext4);
        resultat = -1;

        i = getIntent();
        extras = i.getExtras();
        theme = extras.getString("theme");
        score = extras.getInt("score");

        rep4 = new ArrayList<>();

        try{
            switch (theme){
                case "Astronomie":
                    BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.astronomy);
                    background.setBackground(bg);
                    rep4.add("Venus");
                    rep4.add("Jupiter");
                    rep4.add("Mercury");
                    question4 = new Questions("Quel est le troisième objet astronomique le plus brillant du ciel?",rep4,1);
                    break;
                case "Sport":
                    BitmapDrawable bg3 = (BitmapDrawable)getResources().getDrawable(R.drawable.sport);
                    background.setBackground(bg3);
                    rep4.add("7");
                    rep4.add("8");
                    rep4.add("9");
                    question4 = new Questions("Combien de joueurs y a-t-il dans une équipe de water-polo?",rep4,1);
                    break;
                case "Technologie":
                    BitmapDrawable bg4 = (BitmapDrawable)getResources().getDrawable(R.drawable.technology);
                    background.setBackground(bg4);
                    rep4.add("1901");
                    rep4.add("1910");
                    rep4.add("1911");
                    question4 = new Questions("En quelle année la première radio transatlantique a-t-elle été diffusée?",rep4,1);
                    break;
                case "Animaux":
                    BitmapDrawable bg2 = (BitmapDrawable)getResources().getDrawable(R.drawable.animals2);
                    background.setBackground(bg2);
                    rep4.add("Fourmi");
                    rep4.add("Abeille");
                    rep4.add("Chat");
                    question4 = new Questions("Quelle créature a le plus grand cerveau en proportion de son corps?",rep4,1);
                    break;
                default:
                    break;
            }

            question.setText(question4.question);
            rdReponse1.setText(question4.reponses.get(0));
            rdReponse2.setText(question4.reponses.get(1));
            rdReponse3.setText(question4.reponses.get(2));

        }catch(Exception ex){
            Toast.makeText(FourthQuestion.this,"Error : "+ex.getMessage().toString(), Toast.LENGTH_LONG ).show();
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (resultat == -1) {
                        Toast.makeText(FourthQuestion.this, "Vueillez Choisir Une Question", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(FourthQuestion.this, ResultScore.class);
                        extras.clear();

                        extras.putString("theme", theme);
                        score += resultat;
                        extras.putInt("score", score);
                        intent.putExtras(extras);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.makeText(FourthQuestion.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addScore(View v){

        try {
            selectedRep = rdGroup.getCheckedRadioButtonId();
            rdReponseUser = findViewById(selectedRep);
        }catch (Exception ex){
            Toast.makeText(FourthQuestion.this, "Error : "+ex.getMessage(), Toast.LENGTH_LONG);
        }
        if (selectedRep != -1) {
            resultat = 0;
            if (rdReponseUser.getText().toString().equals(question4.reponses.get(question4.positionTrue - 1))) {
                resultat = 5;
            }
        }
    }
}