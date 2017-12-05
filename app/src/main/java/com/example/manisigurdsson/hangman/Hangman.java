package com.example.manisigurdsson.hangman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Hangman extends AppCompatActivity {

    TextView word_view;
    TextView hidden_view;
    EditText input_field;
    String word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new getData().execute();

        setContentView(R.layout.activity_hangman);
        word_view = findViewById(R.id.word);
        hidden_view = findViewById(R.id.hidden);
        input_field = findViewById(R.id.input_field);
    }

    public void takeGuess(View view) {
        String guess = input_field.getText().toString();
        String build_hidden = hidden_view.getText().toString();
        char[] hiddenArray = build_hidden.toCharArray();
        char[] charArrayWord = word.toCharArray();
        char guessChar = guess.charAt(0);
        for(int i = 0; i < word.length(); i++){
            if(charArrayWord[i] == guessChar){
                hiddenArray[i] = guessChar;
                Log.d("h array: ", ""+hiddenArray[i]);
            }
        }
        build_hidden = "";
        for(int i = 0; i < word.length(); i++){
            build_hidden += hiddenArray[i];
        }
        hidden_view.setText(build_hidden);
        input_field.setText("");
    }


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
            Log.d("json: ", word);
            word_view.setText(word);

            String build_hidden = "";
            for(int i = 0; i < word.length(); i++){
                build_hidden += "-";
            }
            hidden_view.setText(build_hidden);
        }

    }
}