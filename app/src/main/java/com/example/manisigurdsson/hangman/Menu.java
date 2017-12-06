package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.em);
        mp.start();
    }

    public void ClickedTwoPlayer(View view) {
        // TODO
        mp.stop();
    }

    public void ClickedOptions(View view) {
        // TODO
        mp.stop();
    }

    public void ClickedSinglePlayer(View view) {
        Intent intent = new Intent(this, Hangman.class);
        startActivity(intent);
        setContentView(R.layout.activity_hangman);
        mp.stop();

    }
}
