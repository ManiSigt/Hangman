package com.example.manisigurdsson.hangman;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StatsActivity extends AppCompatActivity {

    User user = new User();
    String username;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        username = pref.getString("username", null); // getting String
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        dbRef = FirebaseDatabase.getInstance().getReference();
        getStats();
    }



    public void getStats(){
        DatabaseReference ref = dbRef.child("users");
        Query userQuery = ref.orderByChild(username);

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    if(username.compareTo(singleSnapshot.getKey().toString()) == 0){
                        user = singleSnapshot.getValue(User.class);
                    }
                }

                TextView userName = findViewById(R.id.stats_name);
                TextView userScore = findViewById(R.id.stats_score);
                TextView userWins = findViewById(R.id.stats_wins);
                TextView userLosses = findViewById(R.id.stats_losses);
                TextView userPlayed = findViewById(R.id.stats_played);
                TextView userWinPercent = findViewById(R.id.stats_winpercent);

                userName.setText(user.getName());
                userScore.setText(String.format(String.valueOf(user.getScore()), "%d"));
                userWins.setText(String.format(String.valueOf(user.getWins()), "%d"));
                userLosses.setText(String.format(String.valueOf(user.getLosses()), "%d"));
                userPlayed.setText(String.format(String.valueOf(user.getPlayed()),"%d"));
                double played = ((double)user.getWins()/user.getPlayed())*100;
                userWinPercent.setText(String.format("%.2f",played));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                user.setName("nope");
            }
        });
    }
}
