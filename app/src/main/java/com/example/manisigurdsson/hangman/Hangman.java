package com.example.manisigurdsson.hangman;

<<<<<<< HEAD
=======
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
>>>>>>> 54dcf61a1405ec4036e5dbbccab9f3809452ed42
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
=======
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
>>>>>>> 54dcf61a1405ec4036e5dbbccab9f3809452ed42
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

public class Hangman extends AppCompatActivity {

    TextView word_view;
    TextView hidden_view;
    EditText input_field;
    Button inputbtn;
    String word;
<<<<<<< HEAD

=======
    int MAX_TRIES = 10;
    int tries = 0;
>>>>>>> 54dcf61a1405ec4036e5dbbccab9f3809452ed42
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new getData().execute();

        setContentView(R.layout.activity_hangman);
        word_view = findViewById(R.id.word);
        hidden_view = findViewById(R.id.hidden);
        input_field = findViewById(R.id.input_field);
        inputbtn = findViewById(R.id.btn_guess);
    }

<<<<<<< HEAD
    public void takeGuess(View view) {
        String guess = input_field.getText().toString();
        String build_hidden = hidden_view.getText().toString();
        char[] hiddenArray = build_hidden.toCharArray();
        char[] charArrayWord = word.toCharArray();
        char guessChar = guess.charAt(0);
        for(int i = 0; i < word.length(); i++){
            if(charArrayWord[i] == guessChar){
                hiddenArray[i] = guessChar;
            }
=======
        public void takeGuess (View view){
        String guess = " ";
        if(input_field.getText().toString().trim().length() != 0) {
            guess = input_field.getText().toString().toLowerCase();
>>>>>>> 54dcf61a1405ec4036e5dbbccab9f3809452ed42
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
                Toast.makeText(this, "Sigurvegari!",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Hangman.this, Menu.class);
                startActivity(intent);
            }
            hidden_view.setText(build_hidden);
            input_field.setText("");
        if (tries == MAX_TRIES) {
            Toast.makeText(this, "Gengur betur næst!",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Hangman.this, Menu.class);
            startActivity(intent);
        }
    }

<<<<<<< HEAD
=======

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
>>>>>>> 54dcf61a1405ec4036e5dbbccab9f3809452ed42
    public class getData extends AsyncTask<String, String, String> {

        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL("http://api.wordnik.com:80/v4/words.json/randomWord?hasDictionaryDef=false&minCorpusCount=0&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=-1&minLength=5&maxLength=-1&api_key=6e495aa4345325749497a17c9fb0b55b6a52a540c4a31422f");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject j = new JSONObject(result);
                word  = (String) j.get("word");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new getTranslation().execute();
        }
    }

    public class getTranslation extends AsyncTask <String, String, String> {
        HttpsURLConnection conn;
        @Override
        protected String doInBackground(String... strings) {

            StringBuilder result = new StringBuilder();
            try {
                String urlStr = "https://glosbe.com/gapi/translate?from=eng&dest=isl&phrase=" + word + "&tm=false&format=json";

                URL url = new URL(urlStr);

                conn = (HttpsURLConnection) url.openConnection();
                InputStream stream = new BufferedInputStream(conn.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONObject json = new JSONObject(result)
                        .getJSONArray("tuc")
                        .getJSONObject(0)
                        .getJSONObject("phrase");
                word = (String) json.get("text");
                word_view.setText(word);
                String build_hidden = "";
                for(int i = 0; i < word.length(); i++){
                    build_hidden += "-";
                }
                hidden_view.setText(build_hidden);
            } catch (JSONException e) {
                new getData().execute();
                e.printStackTrace();
            }
        }
    }
}
