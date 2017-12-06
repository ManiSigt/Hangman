package com.example.manisigurdsson.hangman;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class Options extends AppCompatActivity {
    MediaPlayer mp;
    Button on, off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optio);
        on = findViewById(R.id.onbtnid);
        off = findViewById(R.id.offbtnid);
        off.setClickable(false);
    }

    public void offClick(View view) {
        mp.stop();
        mp.reset();
        on.setClickable(true);
        off.setClickable(false);
    }

    public void onclck(View view) {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.em);
        mp.start();
        on.setClickable(false);
        off.setClickable(true);
    }
}