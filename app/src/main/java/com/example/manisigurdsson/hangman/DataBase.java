package com.example.manisigurdsson.hangman;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataBase {

    String username;
    User user;
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

    //fá user úr database
    public User getUser(){
        DatabaseReference ref = mDatabase.child("users");
        Query userQuery = ref.orderByChild(username);

        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    user = singleSnapshot.getValue(User.class);
                    Log.d("getUser: ", user.getName() + " " + user.getScore());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled: ", "cancel.");
                user.setName("nope");
            }
        });
        return user;
    }

    public List<User> getUserList(){
        users = new ArrayList<User>();
        mDatabase.child("users").orderByChild("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int count = 0;
                users = new ArrayList<User>(); // Result
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    String key = dsp.getKey();
                    users.add(dsp.getValue(User.class));
                    count = count +1;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled: ", "cancel.");
            }
        });

        for(int i = 0; i < users.size(); i++){
            Log.d("user: ", users.get(i).getName());
        }
        return users;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
