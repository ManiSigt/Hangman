package com.example.manisigurdsson.hangman;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DataBase {

    String username;
    User user = new User();
    List<User> users;

    //database reference
    private DatabaseReference mDatabase;

    public DataBase(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //saves new user to database, overwrites old user
    public void saveUser(User user){
        DatabaseReference ref = mDatabase.child("users");
        this.user = user;
        this.user.setName(username);
        ref.child(username).setValue(this.user);
    }

    public void userExist(String name){
        DatabaseReference ref = mDatabase.child("users");
        Query check = ref.orderByChild(name);
        username = name;
        check.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.hasChild(username)) {
                    User u = new User(username);
                    saveUser(u);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public List<User> getHighscoreList(){
        users = new ArrayList<User>();
        mDatabase.child("users").orderByChild("score").limitToLast(10).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                users = new ArrayList<User>(); // Result
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    users.add(dsp.getValue(User.class));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled: ", "cancel.");
            }
        });

        return users;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
