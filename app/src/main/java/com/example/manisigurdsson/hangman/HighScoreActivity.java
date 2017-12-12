package com.example.manisigurdsson.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {

    List<User> users;
    String name;
    User user = new User();

    //database reference
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getHighscoreList();
    }

    public void getHighscoreList() {
        users = new ArrayList<User>();
        mDatabase.child("users").orderByChild("score").limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                users = new ArrayList<User>(); // Result
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    users.add(dsp.getValue(User.class));
                }
                populateHighscore();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled: ", "cancel.");
            }
        });
    }

    private void populateHighscore() {
        List<String> personList = new ArrayList<String>();
        for (int i = 0; i < users.size(); i++) {

            personList.add(users.size() - i + ". " + users.get(i).getName() + "\t\t" + users.get(i).getScore());
        }
        Collections.reverse(personList);
        ListView lv = findViewById(R.id.highscore_list);
        final ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personList );

        lv.setAdapter(arrayAdapter);
                lv.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                        name = adapter.getItemAtPosition(position).toString();
                        name = name.replaceAll("[0-9]","");
                        name = name.replaceAll("[\t]","");
                        name = name.replaceAll("[.]","");
                        name = name.substring(1, name.length());

                        populateStatsView();
                    }
                });
    }

    private void populateStatsView() {

        DatabaseReference ref = mDatabase.child("users");
        Query getUserQuery = ref.orderByChild(name);

        getUserQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                user = dataSnapshot.child(name).getValue(User.class);
                setContentView(R.layout.activity_stats);

                TextView userWins = findViewById(R.id.stats_wins);
                TextView userLosses = findViewById(R.id.stats_losses);
                TextView userPlayed = findViewById(R.id.stats_played);
                TextView userWinpercent = findViewById(R.id.stats_winpercent);
                TextView userName = findViewById(R.id.stats_name);
                TextView userScore = findViewById(R.id.stats_score);

                userName.setText(user.getName());
                userScore.setText("" +user.getScore());
                userWins.setText("" +user.getWins());
                userLosses.setText("" +user.getLosses());
                userPlayed.setText("" +user.getPlayed());
                double played = ((double)user.getWins()/user.getPlayed())*100;
                userWinpercent.setText(String.format( "%.2f",played));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled: ", "cancel.");
                user.setName("nope");
            }
        });
    }
}

