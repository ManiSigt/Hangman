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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        key = 0;
    }

    public void ClickedTwoPlayer(View view) {
        // TODO

    }

    public void ClickedOptions(View view) {
        Intent intent = new Intent(this, Options.class);
        intent.putExtra("onOrOFf", key);
        startActivityForResult(intent, REQ_ID);
    }

    public void ClickedSinglePlayer(View view) {
        Intent intent = new Intent(this, Hangman.class);
        startActivity(intent);
        setContentView(R.layout.activity_hangman);

    }
    @Override
    public void onActivityResult(int requestCode, int ResultCode, Intent data) {
        if (requestCode == REQ_ID) {
            if (ResultCode == RESULT_OK) {
                key = data.getIntExtra("MSG", 0);
                if(key == 0){
                    mp.stop();
                    mp.reset();
                }else{
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.em);
                    mp.start();
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
}