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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FirstQuestion extends AppCompatActivity {

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
    List<String> rep1;
    Questions question1;
    String theme;
    int resultat;
    int selectedRep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);
        View background = findViewById(R.id.r1);


        question = findViewById(R.id.question);
        rdGroup = findViewById(R.id.rdGroup);
        rdReponse1 = findViewById(R.id.reponse1);
        rdReponse2 = findViewById(R.id.reponse2);
        rdReponse3 = findViewById(R.id.reponse3);
        next = findViewById(R.id.btnNext);
        resultat = -1;

        try{
            i = getIntent();
            extras = i.getExtras();
            theme = extras.getString("theme");
            score = extras.getInt("score");

            rep1 = new ArrayList<>();

            switch (theme){
                case "Astronomie":
                    BitmapDrawable bg = (BitmapDrawable)getResources().getDrawable(R.drawable.astronomy);
                    background.setBackground(bg);
                    rep1.add("284 400 km");
                    rep1.add("384 400 km");
                    rep1.add("484 400 km");
                    question1 = new Questions("Quelle est la distance entre la Terre et la lune ?",rep1,2);
                    break;
                case "Sport":
                    BitmapDrawable bg3 = (BitmapDrawable)getResources().getDrawable(R.drawable.sport);
                    background.setBackground(bg3);
                    rep1.add("9.58");
                    rep1.add("9.48");
                    rep1.add("9.38");
                    question1 = new Questions("Combien de temps a pris Usain Bolt pour battre le record du monde du 100 mètres?",rep1,1);
                    break;
                case "Technologie":
                    BitmapDrawable bg4 = (BitmapDrawable)getResources().getDrawable(R.drawable.technology);
                    background.setBackground(bg4);
                    rep1.add("6 Juin 1991");
                    rep1.add("6 Juillet 1991");
                    rep1.add("6 Août 1991");
                    question1 = new Questions("Quand la première page Web a-t-elle été mise en ligne?",rep1,3);
                    break;
                case "Animaux":
                    BitmapDrawable bg2 = (BitmapDrawable)getResources().getDrawable(R.drawable.animals2);
                    background.setBackground(bg2);
                    rep1.add("14 000");
                    rep1.add("13 000");
                    rep1.add("12 000");
                    question1 = new Questions("Combien de dents a un escargot au total?",rep1,1);
                    break;
                default:
                    break;
            }

            question.setText(question1.question);
            rdReponse1.setText(question1.reponses.get(0));
            rdReponse2.setText(question1.reponses.get(1));
            rdReponse3.setText(question1.reponses.get(2));


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        if (resultat == -1){
                            Toast.makeText(FirstQuestion.this, "Vueillez Choisir Une Question", Toast.LENGTH_LONG).show();
                        }else{
                            Intent intent = new Intent(FirstQuestion.this,SecondQuestion.class);
                            extras.clear();

                            extras.putString("theme",theme);
                            score += resultat;
                            extras.putInt("score",score);
                            intent.putExtras(extras);
                            startActivity(intent);
                            finish();
                        }
                    }catch (Exception ex){
                        Toast.makeText(FirstQuestion.this,ex.getMessage(),Toast.LENGTH_LONG);
                    }
                }
            });
        }catch (Exception ex){
            Toast.makeText(FirstQuestion.this,"Error : "+ex.getMessage().toString(), Toast.LENGTH_SHORT ).show();
        }
    }

    public void addScore(View v){

        try {
            selectedRep = rdGroup.getCheckedRadioButtonId();
            rdReponseUser = findViewById(selectedRep);
        }catch (Exception ex){
            Toast.makeText(FirstQuestion.this, "Error : "+ex.getMessage(), Toast.LENGTH_LONG);
        }

        if (selectedRep != -1) {
            resultat = 0;
            if (rdReponseUser.getText().toString().equals(question1.reponses.get(question1.positionTrue - 1))) {
                resultat = 5;
            }
        }
    }
}