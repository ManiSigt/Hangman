package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView ord, stig, winorloss, scorewinorloss;
    int WinOrLoss;
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
        WinOrLoss = getIntent().getIntExtra("winorloss", 0);
        String p1 = getIntent().getStringExtra("p1");
        String p2 = getIntent().getStringExtra("p2");

        ord.setText(ordid);

        if(WinOrLoss == 0){
            winorloss.setText("Þú tapaðir :( orðið var :");
            scorewinorloss.setText("þú færð engin stig");
            stig.setText(" ");
        }else if(WinOrLoss == 1) {
            stig.setText(Integer.toString(stigin));
        }else if(WinOrLoss == 2){
            winorloss.setText(p2 + " Vann! orðið var :");
            scorewinorloss.setText(p1 + " þú verður að gera betur en þetta!");
            stig.setText(" ");
        }else if (WinOrLoss == 3){
            winorloss.setText(p1 + " Vann! orðið var :");
            scorewinorloss.setText(p2 + " þú verður að gera betur en þetta!");
            stig.setText(" ");
        }
    }

    public void jaClick(View view) {
        if(WinOrLoss == 2 || WinOrLoss == 3){
            Intent intent = new Intent(this, TwoPlayerOptions.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, DifficultySettings.class);
            startActivity(intent);
        }
    }

    public void neiClick(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}
