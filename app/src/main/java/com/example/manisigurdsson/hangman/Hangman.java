package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class Hangman extends AppCompatActivity {

    TextView hidden_view, rubieview;
    EditText editText;
    String word, username, wordsToTranslate;
    User user;
    ImageView img;
    TextWatcher tw;
    List<String> wordlist = new ArrayList<>();
    List<String> words = new ArrayList<>();

    private ProgressBar spinner;

    int MAX_TRIES;
    int tries = 0;
    int score;

    DataBase db;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        username = pref.getString("username", null); // getting String
        String pref_words = pref.getString("words", null);

        if(pref_words != null) {
            if (pref_words.length() > 2) {
                pref_words = pref_words.replace(".", "");
                pref_words = pref_words.replace(",", "");
                pref_words = pref_words.replace("[", "");
                pref_words = pref_words.replace("]", "");
                String arr[] = pref_words.split(" ");
                words = new ArrayList<>();
                words = Arrays.asList(arr);
            }
        }

        dbRef = FirebaseDatabase.getInstance().getReference();
        db = new DataBase();
        db.userExist(username);

        getUser();

        int difficulty = getIntent().getIntExtra("msg", 1);

        hidden_view = findViewById(R.id.hidden);
        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        new getData().execute();

        MyKeyboard keyboard = findViewById(R.id.keyboard);
        editText = findViewById(R.id.keyboard_input);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        pushKey();

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);
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

    public void pushKey() {

        editText.addTextChangedListener(tw = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String buttonText = editText.getText().toString();
                takeGuess(buttonText);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                editText.removeTextChangedListener(tw);
                editText.setText("");
                editText.addTextChangedListener(tw);
            }
        });
    }

    public void getUser(){

        DatabaseReference ref = dbRef.child("users");
        Query userQuery = ref.orderByChild(username);

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

                    if(username.compareTo(singleSnapshot.getKey()) == 0){

                        user = singleSnapshot.getValue(User.class);
                        assert user != null;
                        Log.e("userX: ", user.getName() +" " + user.getScore() + " " + user.getRubies());

                        Log.d("hasChild A: ", user.getName());
                        rubieview = findViewById(R.id.rubieid);
                        rubieview.setText(String.format(String.valueOf(user.getRubies()), "%d"));
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled: ", "cancel.");
                user.setName("nope");
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

            Log.d("CHECK: ", check);
            if (check.equals(theWord.toString())) {
                user.addWin();
                user.addScore(score/word.length());
                user.addRubies(1);

                Intent intent = new Intent(Hangman.this, Result.class);
                intent.putExtra("word", word);
                intent.putExtra("score", score/word.length());
                intent.putExtra("winorloss", 1);
                startActivity(intent);
            }

            hidden_view.setText(build_hidden);

            if (tries == MAX_TRIES) {
                user.addLoss(); //bæta við tapi
                final Intent intent = new  Intent(Hangman.this, Result.class);
                intent.putExtra("word", word);
                intent.putExtra("score", 0);
                intent.putExtra("winorloss", 0);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        startActivity(intent);
                    }
                }, 2000);

            }
        }
    }

    @Override
    protected void onStop() {

        super.onStop();
        db.saveUser(user);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String wordsToSave = words.toString();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("words", wordsToSave);
        editor.apply();
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

    public void hintClick(View view) {

        char correct;

        if(user.getRubies() > 0 ) {
            String check = hidden_view.getText().toString();
            check = check.replace(" ", "");
            Log.d("XXXX: ", check);

            for (int i = 0; i < word.length(); i++) {

                if (check.charAt(i) == '_') {

                    correct = word.toUpperCase().charAt(i);

                    Toast.makeText(this, "Prófaðu þennan staf : " + correct,
                            Toast.LENGTH_LONG).show();
                    user.removeRubies(1);
                    rubieview.setText(String.format(String.valueOf(user.getRubies()), "%d"));
                    break;
                }
            }
        }else {
            Toast.makeText(this, "Þú átt ekki nógu margar rúbínur til að fá vísbendingu",
                    Toast.LENGTH_LONG).show();
        }
    }

    public class getData extends AsyncTask<String, String, String> {

        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            if (words.size() == 0) {
                StringBuilder result = new StringBuilder();

                try {
                    String random_url = Paths.getRandom_url();
                    URL url = new URL(random_url);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
                return result.toString();
            }
            return "no-need";
        }

        @Override
        protected void onPostExecute(String result) {

            if(result.equals("no-need")){

                if(words.size() != 0){
                    word = words.get(0).toLowerCase();
                    words = words.subList(1, words.size());
                    String build_hidden = "";

                    for(int i = 0; i < word.length(); i++){
                        build_hidden += " _";
                    }

                    hidden_view.setText(build_hidden);
                    spinner.setVisibility(View.INVISIBLE);;
                }
                else{
                    new getData().execute();
                }
            }
            else{
                try {
                    JSONArray j = new JSONArray(result);
                    for (int i = 0; i < j.length(); i++) {
                        wordlist.add(j.getJSONObject(i).get("word").toString());
                    }

                    new getTranslation().execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class getTranslation extends AsyncTask <String, String, String> {

        HttpsURLConnection conn;

        @Override
        protected String doInBackground(String... strings) {

            wordsToTranslate = "";

            for(int i = 0; i < wordlist.size(); i++){
                wordsToTranslate += wordlist.get(i);
                if(wordlist.size() != i){
                    wordsToTranslate += "%20";
                }
            }

            StringBuilder result = new StringBuilder();

            try {
                String t_url = Paths.getTransl_url1() + wordsToTranslate + Paths.getTransl_url2();

                Paths.setTransl_url(t_url);
                String transl_url = Paths.getTr_url();
                URL url = new URL(transl_url);
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
            try {

                String json = new JSONObject(result)
                        .getJSONArray("text").getString(0);
                String arr[] = json.split(" ");
                List<String> arraylist = new ArrayList<>();
                words = new ArrayList<>();
                arraylist = Arrays.asList(arr);
                int found = 0;

                for(int k = 0; k < arraylist.size(); k++){
                    found = 0;

                    for(int i = 0; i < wordlist.size(); i++){
                        if(0 == arraylist.get(k).compareTo(wordlist.get(i))){
                            found++;
                        }
                    }

                    if(found == 0 && !arraylist.get(k).contains("-")){
                        words.add(arraylist.get(k));
                    }
                }

                new getData().execute();

            } catch (JSONException e) {
                e.printStackTrace();
                new getData().execute();
            }
        }
    }

}