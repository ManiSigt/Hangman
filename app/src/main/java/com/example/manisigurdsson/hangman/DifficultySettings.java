package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DifficultySettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_settings);
    }

    public void easyClick(View view) {
        Intent intent = new Intent(this, Hangman.class);
        intent.putExtra("msg", 1);
        startActivity(intent);
    }

    public void mediumClick(View view) {
        Intent intent = new Intent(this, Hangman.class);
        intent.putExtra("msg", 2);
        startActivity(intent);
    }

    public void hardClick(View view) {
        Intent intent = new Intent(this, Hangman.class);
        intent.putExtra("msg" , 3);
        startActivity(intent);
    }
}
