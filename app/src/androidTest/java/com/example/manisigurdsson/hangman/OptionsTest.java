package com.example.manisigurdsson.hangman;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.WindowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by manisigurdsson on 12/12/17.
 */
public class OptionsTest {
    @Rule
    public ActivityTestRule<Options> OptionsTestRule =
            new ActivityTestRule<>(Options.class, true, false);
    @Rule
    public IntentsTestRule<Options> intentsTestRule =
            new IntentsTestRule<>(Options.class);
    private Options activity;



    @Before
    public void setUp(){

        OptionsTestRule.launchActivity(new Intent());
        activity = OptionsTestRule.getActivity();
        noSleep(activity);
    }
    @After
    public void tearDown(){ }

    @Test
    public void testButtonOn(){
        ViewInteraction btn = onView(withId(R.id.onbtnid));
        ViewInteraction offbtn = onView(withId(R.id.offbtnid));
        btn.perform(click());
        offbtn.check(matches(isClickable()));
    }
    @Test
    public void testButtonOff(){
        ViewInteraction btn = onView(withId(R.id.onbtnid));
        ViewInteraction offbtn = onView(withId(R.id.offbtnid));
        btn.perform(click());
        offbtn.perform(click());
        btn.check(matches(isClickable()));
    }
    @Test
    public void testSubmitBtn(){
        Intent resultData = new Intent();
        resultData.putExtra("MSG", 1);
        Instrumentation.ActivityResult result =
                new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(toPackage("com.android.Menu")).respondWith(result);

        onView(withId(R.id.submitid)).perform(click());

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