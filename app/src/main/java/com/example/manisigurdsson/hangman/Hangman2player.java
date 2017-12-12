package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Hangman2player extends AppCompatActivity {

    TextView word_view;
    TextView hidden_view;
    EditText input_field;
    String word;
    TextWatcher tw;
    ImageView img;


    int MAX_TRIES = 9;
    int tries = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman2player);

        img = findViewById(R.id.imageView);
        word_view = findViewById(R.id.word);
        hidden_view = findViewById(R.id.hidden);
        word = getIntent().getStringExtra("secret");

        String hidden = "";

        for(int i = 0; i < word.length(); i++){
            hidden += "-";
        }
        hidden_view.setText(hidden);
        word_view.setText(word);
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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String buttonText = input_field.getText().toString();
                if(buttonText != null && buttonText != " " && buttonText != "") {
                    takeGuess(buttonText);
                }
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
                        Log.d("SSSSDDDDDDSSSSSS", s);
                        img.setImageResource(R.drawable.hangman2);
                    } else if (tries == 2) {
                        img.setImageResource(R.drawable.hangman3);
                    } else if (tries == 3) {
                        img.setImageResource(R.drawable.hangman4);
                    } else if (tries == 4) {
                        img.setImageResource(R.drawable.hangman5);
                    } else if (tries == 5) {
                        img.setImageResource(R.drawable.hangman6);
                    } else if (tries == 6) {
                        img.setImageResource(R.drawable.hangman7);
                    } else if (tries == 7) {
                        img.setImageResource(R.drawable.hangman8);
                    } else if (tries == 8) {
                        img.setImageResource(R.drawable.hangman9);
                    } else if (tries == 9) {
                        img.setImageResource(R.drawable.hangman10);
                    }
                } else if (MAX_TRIES == 6) {
                    if (tries == 1) {
                        img.setImageResource(R.drawable.hangman3);
                    } else if (tries == 2) {
                        img.setImageResource(R.drawable.hangman4);
                    } else if (tries == 3) {
                        img.setImageResource(R.drawable.hangman5);
                    } else if (tries == 4) {
                        img.setImageResource(R.drawable.hangman7);
                    } else if (tries == 5) {
                        img.setImageResource(R.drawable.hangman9);
                    } else if (tries == 6) {
                        img.setImageResource(R.drawable.hangman10);
                    }
                } else {
                    if (tries == 1) {
                        img.setImageResource(R.drawable.hangman3);
                    } else if (tries == 2) {
                        img.setImageResource(R.drawable.hangman9);
                    } else if (tries == 3) {
                        img.setImageResource(R.drawable.hangman10);
                    }
                }
            } else {
                Toast.makeText(this, "Rétt!",
                        Toast.LENGTH_SHORT).show();
            }
            if (build_hidden.toString().equals(theWord.toString())) {

                Toast.makeText(this, "Sigurvegari!",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Hangman2player.this, Result.class);
                intent.putExtra("word", word);
                intent.putExtra("winorloss", 1);
                startActivity(intent);
            }
            hidden_view.setText(build_hidden);
            if (tries == MAX_TRIES) {
                Toast.makeText(this, "Gengur betur næst!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Hangman2player.this, Result.class);
                intent.putExtra("word", word);
                intent.putExtra("score", 0);
                intent.putExtra("winorloss", 0);
                startActivity(intent);
            }
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

    public void hintClick(View view) {
        char correct;
        for (int i = 0; i < word.length(); i++) {
            if (hidden_view.getText().toString().charAt(i) == '-') {
                correct = word.charAt(i);
                Toast.makeText(this, "Prófaðu þennan staf : " + correct,
                        Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
