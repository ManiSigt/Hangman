package com.example.manisigurdsson.hangman;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Laki on 07/12/2017.
 */

public class DataBase {

    String username;
    User user = new User();

    //database reference
    private DatabaseReference mDatabase;

    public DataBase(String username){

        mDatabase = FirebaseDatabase.getInstance().getReference();

        this.username = username;
    }

    //saves new user to database, overwrites old user
    public void saveUser(User user){
        DatabaseReference ref = mDatabase.child("users");
        user.setName(username);
        ref.child(username).setValue(user);
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
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("onCancelled: ", "cancel.");
            }
        });
        return user;
    }
}
