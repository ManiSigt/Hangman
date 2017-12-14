package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Hangman2player extends AppCompatActivity {

    TextView hidden_view;
    EditText input_field;
    String word;
    TextWatcher tw;
    ImageView img;
    String p1name, p2name;

    int MAX_TRIES = 9;
    int tries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman2player);

        img = findViewById(R.id.imageView);
        hidden_view = findViewById(R.id.hidden);

        if(getIntent().getStringExtra("secret") != null){
            word = getIntent().getStringExtra("secret").toLowerCase();
        }
        else{
            word = "móðurborð";
        }

        p1name = getIntent().getStringExtra("p1");
        p2name = getIntent().getStringExtra("p2");
        String hidden = "";

        for(int i = 0; i < word.length(); i++){
            hidden += " _";
        }

        hidden_view.setText(hidden);
        MyKeyboard keyboard = findViewById(R.id.keyboard);
        input_field = findViewById(R.id.keyboard_input);
        input_field.setRawInputType(InputType.TYPE_CLASS_TEXT);
        input_field.setTextIsSelectable(true);

        pushKey();

        InputConnection ic = input_field.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
    }

    public void pushKey() {
        input_field.addTextChangedListener(tw = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String buttonText = input_field.getText().toString();
                takeGuess(buttonText);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                input_field.removeTextChangedListener(tw);
                input_field.setText("");
                input_field.addTextChangedListener(tw);
            }
        });
    }

    public void takeGuess (String s){
        StringBuilder build_hidden = new StringBuilder(hidden_view.getText().toString());
        StringBuilder theWord = new StringBuilder(word);
        if(s != null) {
            char guessChar = s.charAt(0);

            if (guess(build_hidden, word.toLowerCase(), guessChar) == 0) {
                Toast.makeText(this, "Ekki rétt ",
                        Toast.LENGTH_SHORT).show();
                tries++;

                if (MAX_TRIES == 9) {
                    if (tries == 1) {
                        img.setImageResource(R.drawable.hangman2_burned);
                    } else if (tries == 2) {
                        img.setImageResource(R.drawable.hangman3_burned);
                    } else if (tries == 3) {
                        img.setImageResource(R.drawable.hangman4_burned);
                    } else if (tries == 4) {
                        img.setImageResource(R.drawable.hangman5_burned);
                    } else if (tries == 5) {
                        img.setImageResource(R.drawable.hangman6_burned);
                    } else if (tries == 6) {
                        img.setImageResource(R.drawable.hangman7_burned);
                    } else if (tries == 7) {
                        img.setImageResource(R.drawable.hangman8_burned);
                    } else if (tries == 8) {
                        img.setImageResource(R.drawable.hangman9_burned);
                    } else if (tries == 9) {
                        img.setImageResource(R.drawable.hangman10_burned);
                    }
                } else if (MAX_TRIES == 6) {
                    if (tries == 1) {
                        img.setImageResource(R.drawable.hangman3_burned);
                    } else if (tries == 2) {
                        img.setImageResource(R.drawable.hangman4_burned);
                    } else if (tries == 3) {
                        img.setImageResource(R.drawable.hangman5_burned);
                    } else if (tries == 4) {
                        img.setImageResource(R.drawable.hangman7_burned);
                    } else if (tries == 5) {
                        img.setImageResource(R.drawable.hangman9_burned);
                    } else if (tries == 6) {
                        img.setImageResource(R.drawable.hangman10_burned);
                    }
                } else {
                    if (tries == 1) {
                        img.setImageResource(R.drawable.hangman3_burned);
                    } else if (tries == 2) {
                        img.setImageResource(R.drawable.hangman9_burned);
                    } else if (tries == 3) {
                        img.setImageResource(R.drawable.hangman10_burned);
                    }
                }
            }

            String check = build_hidden.toString();
            check = check.replaceAll("_", "");
            check = check.replaceAll(" ", "");

            if (check.equals(theWord.toString())) {
                Intent intent = new Intent(Hangman2player.this, Result.class);
                intent.putExtra("p1", p1name);
                intent.putExtra("p2", p2name);
                intent.putExtra("word", word);
                intent.putExtra("winorloss", 2);
                startActivity(intent);
            }
            hidden_view.setText(build_hidden);

            if (tries == MAX_TRIES) {
                final Intent intent = new Intent(Hangman2player.this, Result.class);
                intent.putExtra("word", word);
                intent.putExtra("p1", p1name);
                intent.putExtra("p2", p2name);
                intent.putExtra("score", 0);
                intent.putExtra("winorloss", 3);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        startActivity(intent);
                    }
                }, 2000);
                startActivity(intent);
            }
        }
    }

    public int guess(StringBuilder hiddenArray, String theWord, char guessChar){
        int correctChars = 0;
        for(int i = 0; i < word.length(); i++){
            if(guessChar == theWord.charAt(i)){
                hiddenArray.setCharAt(i*2+1, guessChar);
                correctChars ++;
            }
        }
        return correctChars;
    }
}
