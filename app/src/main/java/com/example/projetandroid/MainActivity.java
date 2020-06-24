package com.example.projetandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button start;
    TextView nom;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start =findViewById(R.id.btnStart);
        nom = findViewById(R.id.txtName);
        sharedPreferences = getBaseContext().getSharedPreferences("Shared", MODE_PRIVATE);

    }

    public void onClickBtnStart(View v){
        if(nom.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Veuillez saisir votre pseudo pour commencer.",Toast.LENGTH_SHORT).show();
        }else{
            sharedPreferences.edit().putString("nom", nom.getText().toString()).apply();
            Intent intent = new Intent(MainActivity.this , RecyclerViewActivity.class);
            startActivity(intent);
        }
    }
}