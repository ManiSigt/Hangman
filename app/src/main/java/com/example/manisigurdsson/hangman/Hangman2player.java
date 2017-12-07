package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Hangman2player extends AppCompatActivity {

    TextView word_view;
    TextView hidden_view;
    EditText input_field;
    Button inputbtn;
    String word;

    int MAX_TRIES = 10;
    int tries = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman2player);
        word_view = findViewById(R.id.word);
        hidden_view = findViewById(R.id.hidden);
        input_field = findViewById(R.id.input_field);
        inputbtn = findViewById(R.id.btn_guess);
        word = getIntent().getStringExtra("secret");
        String hidden = "";
        for(int i = 0; i < word.length(); i++){
            hidden += "-";
        }
        hidden_view.setText(hidden);
        word_view.setText(word);
    }
    public void takeGuess (View view){
        String guess = " ";
        if(input_field.getText().toString().trim().length() != 0) {
            guess = input_field.getText().toString().toLowerCase();
        }
        StringBuilder build_hidden = new StringBuilder(hidden_view.getText().toString());
        StringBuilder theWord = new StringBuilder(word);
        char guessChar = guess.charAt(0);
        if (guessChar == ' ') {
            Toast.makeText(this, "Ekki rétt",
                    Toast.LENGTH_SHORT).show();
            tries++;
        }else if (guess(build_hidden, word.toLowerCase(), guessChar) == 0) {
            Toast.makeText(this, "Ekki rétt ",
                    Toast.LENGTH_SHORT).show();
            tries++;
        }
        else {
            Toast.makeText(this, "Rétt!" ,
                    Toast.LENGTH_SHORT).show();
        }
        if (build_hidden.toString().equals(theWord.toString())) {
            Toast.makeText(this, getIntent().getStringExtra("p2") + " er sigurvegari!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Hangman2player.this, Menu.class);
            startActivity(intent);
        }
        hidden_view.setText(build_hidden);
        input_field.setText("");
        if (tries == MAX_TRIES) {
            Toast.makeText(this, getIntent().getStringExtra("p1") + " er sigurvegari!",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Hangman2player.this, Menu.class);
            startActivity(intent);
        }
    }


    public int guess(StringBuilder hiddenArray, String theWord, char guessChar){
        int correctChars = 0;
        for(int i = 0; i < word.length(); i++){
            if(guessChar == hiddenArray.charAt(i)){
                return 0;
            }
            if(guessChar == theWord.charAt(i)){
                hiddenArray.setCharAt(i, guessChar);
                correctChars ++;
            }
        }
        return correctChars;
    }



}
