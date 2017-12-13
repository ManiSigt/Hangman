package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TwoPlayerOptions extends AppCompatActivity {
    EditText p1, p2, secret;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_options);
        p1 = findViewById(R.id.player1ID);
        p2 = findViewById(R.id.player2ID);
        secret = findViewById(R.id.secretID);
        btn_submit = findViewById(R.id.submitbtnid);
    }

    public void submitClick(View view) {
        String p1name = p1.getText().toString();
        String p2name = p2.getText().toString();
        String sec = secret.getText().toString();
        Intent intent = new Intent(TwoPlayerOptions.this, Hangman2player.class);
        intent.putExtra("p1", p1name);
        intent.putExtra("p2", p2name);
        intent.putExtra("secret", sec);
        startActivity(intent);

    }
}
