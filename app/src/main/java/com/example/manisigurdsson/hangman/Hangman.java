package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class Hangman extends AppCompatActivity {

    TextView word_view;
    TextView hidden_view;
    EditText input_field, editText;
    Button inputbtn;
    String word, username;
    User user;
    ImageView img;

    int MAX_TRIES;
    int tries = 0;
    int score;

    DataBase db;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new getData().execute();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        username = pref.getString("username", null); // getting String

        dbRef = FirebaseDatabase.getInstance().getReference();
        db = new DataBase();
        db.userExist(username);
        getUser();

        int difficulty = getIntent().getIntExtra("msg", 1);


        setContentView(R.layout.activity_hangman);
        word_view = findViewById(R.id.word);
        hidden_view = findViewById(R.id.hidden);
        input_field = findViewById(R.id.input_field);
        inputbtn = findViewById(R.id.btn_guess);


        MyKeyboard keyboard = findViewById(R.id.keyboard);

        editText = findViewById(R.id.keyboard_input);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
        List<User> list = db.getHighscoreList();

        img = findViewById(R.id.imageView);
        if(difficulty == 1){
            MAX_TRIES = 9;
            score = 1000;
        }else if (difficulty == 2){
            MAX_TRIES = 6;
            score = 2000;
        }else{
            MAX_TRIES = 3;
            score = 3000;
        }

    }

    public void getUser(){
        DatabaseReference ref = dbRef.child("users");
        Query userQuery = ref.orderByChild(username);

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    user = singleSnapshot.getValue(User.class);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled: ", "cancel.");
                user.setName("nope");
            }
        });
    }

    public void takeGuess (View view){
    String guess = " ";
    if(editText.getText().toString().trim().length() != 0) {
        guess = editText.getText().toString().toLowerCase();
    }
    StringBuilder build_hidden = new StringBuilder(hidden_view.getText().toString());
    StringBuilder theWord = new StringBuilder(word);
        char guessChar = guess.charAt(0);
        if (guessChar == ' ') {
            Toast.makeText(this, "Ekki rétt",
                    Toast.LENGTH_SHORT).show();
            tries++;
            if(MAX_TRIES == 9) {
                if (tries == 1) {
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
            }else if(MAX_TRIES == 6){
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
            }else{
                if (tries == 1) {
                    img.setImageResource(R.drawable.hangman3);
                } else if (tries == 2) {
                    img.setImageResource(R.drawable.hangman9);
                } else if (tries == 3) {
                    img.setImageResource(R.drawable.hangman10);
                }
            }
        }else if (guess(build_hidden, word.toLowerCase(), guessChar) == 0) {
            Toast.makeText(this, "Ekki rétt ",
                    Toast.LENGTH_SHORT).show();
            tries++;
            if(MAX_TRIES == 9) {
                if (tries == 1) {
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
            }else if(MAX_TRIES == 6){
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
            }else{
                if (tries == 1) {
                    img.setImageResource(R.drawable.hangman3);
                } else if (tries == 2) {
                    img.setImageResource(R.drawable.hangman9);
                } else if (tries == 3) {
                    img.setImageResource(R.drawable.hangman10);
                }
            }
        }
        else {
            Toast.makeText(this, "Rétt!" ,
                    Toast.LENGTH_SHORT).show();
        }
        if (build_hidden.toString().equals(theWord.toString())) {
            user.addWin();
            user.addScore(score/word.length());
            Toast.makeText(this, "Sigurvegari!",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Hangman.this, Menu.class);
            startActivity(intent);
        }
        hidden_view.setText(build_hidden);
        editText.setText("");
        if (tries == MAX_TRIES) {
            user.addLoss(); //bæta við tapi
            Toast.makeText(this, "Gengur betur næst!",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Hangman.this, Menu.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.saveUser(user);
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