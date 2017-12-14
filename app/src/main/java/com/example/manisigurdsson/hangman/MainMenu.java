package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainMenu extends AppCompatActivity {
    public static final int REQ_ID = 1111;
    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        if(isLoggedIn()){
            Intent intent = new Intent(MainMenu.this, Menu.class);
            startActivityForResult(intent, REQ_ID);
        }
        else{
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();

                if (profile != null) {
                    saveUser(profile.getName());
                }

                Intent intent = new Intent(MainMenu.this, Menu.class);
                startActivityForResult(intent, REQ_ID);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) { }
        });}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isLoggedIn() {
        Profile profile = Profile.getCurrentProfile();

        if (profile != null) {
            saveUser(profile.getName());
        }

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public void saveUser (String user){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        Editor editor = pref.edit();

        editor.putString("username", user);
        editor.apply();
    }
}
