package com.example.manisigurdsson.hangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {

    List<User> users;
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
            personList.add(users.get(i).getName() + " " + users.get(i).getScore());
        }
        Collections.reverse(personList);
        ListView lv = findViewById(R.id.highscore_list);
        final ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personList );

        lv.setAdapter(arrayAdapter);
                /*lv.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                        String name = adapter.getItemAtPosition(position).toString();

                        Person p = map.getPerson(name);
                        setContentView(R.layout.view);

                        TextView viewName = findViewById(R.id.viewName);
                        TextView viewAddress = findViewById(R.id.viewAddress);
                        TextView viewPhone = findViewById(R.id.viewPhone);

                        viewName.setText(p.name);
                        viewAddress.setText(p.address);
                        viewPhone.setText(p.phone);
                    }
                });*/
    }
}
