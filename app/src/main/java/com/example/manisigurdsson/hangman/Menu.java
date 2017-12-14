package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    int REQ_ID = 1111;
    int key;
    MediaPlayer mp;
    boolean isplaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        key = 0;
        isplaying = false;
    }

    public void clickedTwoPlayer(View view) {
        Intent intent = new Intent(this, TwoPlayerOptions.class);
        startActivity(intent);

    }

    public void clickedOptions(View view) {
        Intent intent = new Intent(this, Options.class);
        intent.putExtra("onOrOFf", key);
        startActivityForResult(intent, REQ_ID);
    }

    public void clickedSinglePlayer(View view) {
        Intent intent = new Intent(this, DifficultySettings.class);
        startActivity(intent);

    }
    @Override
    public void onActivityResult(int requestCode, int ResultCode, Intent data) {
        if (requestCode == REQ_ID) {
            if (ResultCode == RESULT_OK) {
                key = data.getIntExtra("MSG", 0);
                if(key == 0 && isplaying){
                    mp.stop();
                    mp.reset();
                    isplaying = false;
                }else if(key == 1 && !isplaying){
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.em);
                    mp.start();
                    isplaying = true;
                }
            } else {
                Toast.makeText(this, "Villa!",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Villa!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void clickedHighscore(View view) {
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }

    public void clickedStats(View view) {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }
}