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
import static org.junit.Assert.assertNotNull;

public class ResultTest {

    @Rule
    public ActivityTestRule<Result> ResultTestRule =
            new ActivityTestRule<>(Result.class, true, false);

    private Result activity;

    @Before
    public void setUp(){
        ResultTestRule.launchActivity(new Intent());
        activity = ResultTestRule.getActivity();
        noSleep(activity);
    }

    @After
    public void tearDown(){ }

    @Test
    public void testButtonYes(){
        ViewInteraction btn = onView(withId(R.id.jaId));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DifficultySettings.class.getName(), null, false);

        btn.perform(click());

        DifficultySettings nextActivity = (DifficultySettings) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }

    @Test
    public void testButtonNo(){
        ViewInteraction btn = onView(withId(R.id.neiId));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Menu.class.getName(), null, false);

        btn.perform(click());

        Menu nextActivity = (Menu) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
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