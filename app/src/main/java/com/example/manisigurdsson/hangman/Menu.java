package com.example.manisigurdsson.hangman;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.media.MediaPlayer;
>>>>>>> 3ebec7302f4fdb236c3ffecf93b2a19e8633e0c7
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
        Intent intent = new Intent(this, Hangman.class);
        startActivity(intent);
        setContentView(R.layout.activity_hangman);

    }
}
