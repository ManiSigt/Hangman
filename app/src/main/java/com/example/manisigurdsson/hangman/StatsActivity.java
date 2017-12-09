package com.example.manisigurdsson.hangman;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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
                    user = singleSnapshot.getValue(User.class);
                }

                TextView userWins = findViewById(R.id.stats_wins);
                TextView userLosses = findViewById(R.id.stats_losses);
                TextView userPlayed = findViewById(R.id.stats_played);
                TextView userWinpercent = findViewById(R.id.stats_winpercent);

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
