package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
