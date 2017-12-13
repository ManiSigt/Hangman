package com.example.manisigurdsson.hangman;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.WindowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by manisigurdsson on 13/12/17.
 */
public class DifficultySettingsTest {
    @Rule
    public ActivityTestRule<DifficultySettings> DifficultySettingsTestRule =
            new ActivityTestRule<>(DifficultySettings.class, true, false);
    private DifficultySettings activity;



    @Before
    public void setUp(){

        DifficultySettingsTestRule.launchActivity(new Intent());
        activity = DifficultySettingsTestRule.getActivity();
        noSleep(activity);
    }
    @After
    public void tearDown(){ }

    @Test
    public void testButtoneasy(){
            // HJALP VEIT EKKERT!"#$%!"#$!"#$
    }




    private void noSleep(final Activity activity){
        Runnable wakeUp = () -> activity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        );
        activity.runOnUiThread(wakeUp);

    }

}