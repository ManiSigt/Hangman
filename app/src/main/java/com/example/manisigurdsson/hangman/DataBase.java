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

    //database reference
    private DatabaseReference mDatabase;

    public DataBase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //saves new user to database, overwrites old user
    public void saveUser(User user) {
        DatabaseReference ref = mDatabase.child("users");
        this.user = user;
        this.user.setName(username);
        ref.child(username).setValue(this.user);
    }

    public void userExist(String name) {
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




}
