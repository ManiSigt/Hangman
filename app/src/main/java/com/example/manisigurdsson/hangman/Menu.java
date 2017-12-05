package com.example.manisigurdsson.hangman;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.em);
        mp.start();
    }

    public void ClickedTwoPlayer(View view) {
        // TODO
    }

    public void ClickedOptions(View view) {
        // TODO
    }

    public void ClickedSinglePlayer(View view) {
        // TODO
    }
}
