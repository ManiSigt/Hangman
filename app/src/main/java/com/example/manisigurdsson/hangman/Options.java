package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {

    Button on, off, submit;
    int musicOnOrOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optio);
        on = findViewById(R.id.onbtnid);
        off = findViewById(R.id.offbtnid);
        submit = findViewById(R.id.submitid);
        int key = getIntent().getIntExtra("onOrOFf", 0);
        
        if(key == 0){
            off.setClickable(false);
        }else{
            on.setClickable(false);
        }
    }

    public void offClick(View view) {
        musicOnOrOff = 0;
        on.setClickable(true);
        off.setClickable(false);
    }

    public void onclck(View view) {
        musicOnOrOff = 1;
        on.setClickable(false);
        off.setClickable(true);
    }

    public void SubmitClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("MSG", musicOnOrOff);
        setResult(RESULT_OK, intent);
        finish();
    }
}