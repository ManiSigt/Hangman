package com.example.manisigurdsson.hangman;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
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
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by manisigurdsson on 12/12/17.
 */
public class OptionsTest {
    @Rule
    public ActivityTestRule<Options> OptionsTestRule =
            new ActivityTestRule<>(Options.class, true, false);
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
   /* @Test
    public void testSubmitBtn(){
        ViewInteraction btn = onView(withId(R.id.submitid));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Menu.class.getName(), null, false);

        btn.perform(click());

        assertThat(OptionsTestRule.getActivityResult(), has(Activity.RESULT_OK));


        Menu nextActivity = (Menu) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }*/


    private void noSleep(final Activity activity){
        Runnable wakeUp = () -> activity.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        );
        activity.runOnUiThread(wakeUp);

    }

}