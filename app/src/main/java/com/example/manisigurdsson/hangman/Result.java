package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView ord, stig, winorloss, scorewinorloss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ord = findViewById(R.id.wordId);
        stig = findViewById(R.id.scoreID);
        winorloss = findViewById(R.id.winOrLoss);
        scorewinorloss = findViewById(R.id.Scoreset);
        String ordid = getIntent().getStringExtra("word");
        int stigin = getIntent().getIntExtra("score", 0);
        int WinOrLoss = getIntent().getIntExtra("winorloss", 0);
        ord.setText(ordid);
        if(WinOrLoss == 0){
            winorloss.setText("Þú tapaðir :( orðið var :");
            scorewinorloss.setText("þú færð engin stig");
            stig.setText(" ");
        }else {
            stig.setText(stigin);
        }
    }

    public void jaclick(View view) {
        Intent intent = new Intent(this, DifficultySettings.class);
        startActivity(intent);
    }

    public void neiClick(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}
