package com.example.manisigurdsson.hangman;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
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
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by manisigurdsson on 14/12/17.
 */
public class TwoPlayerOptionsTest {
    @Rule
    public ActivityTestRule<TwoPlayerOptions> TwoPlayerOptionTestRule =
            new ActivityTestRule<>(TwoPlayerOptions.class, true, false);
    private TwoPlayerOptions activity;



    @Before
    public void setUp(){

        TwoPlayerOptionTestRule.launchActivity(new Intent());
        activity = TwoPlayerOptionTestRule.getActivity();
        noSleep(activity);
    }
    @After
    public void tearDown(){ }

    @Test
    public void TestNotValid(){
        ViewInteraction btn = onView(withId(R.id.submitbtnid));
        ViewInteraction word = onView(withId(R.id.secretID));
        btn.perform(click());
        word.check(matches(hasFocus()));
        word.check(matches(hasErrorText(activity.getString(R.string.notValid))));

    }
    @Test
    public void nextActivity() {
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, DifficultySettings.class);
        intent.putExtra("p1", "amma");
        intent.putExtra("p2", "afi");
        intent.putExtra("secret", "bakki");

        ViewInteraction btn = onView(withId(R.id.submitbtnid));
        ViewInteraction p1 = onView(withId(R.id.player1ID));
        ViewInteraction p2 = onView(withId(R.id.player2ID));
        ViewInteraction word = onView(withId(R.id.secretID));
        p1.perform(replaceText("amma"));
        p2.perform(replaceText("afi"));
        word.perform(replaceText("bakki"));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Hangman2player.class.getName(), null, false);

        btn.perform(click());


        Hangman2player nextActivity = (Hangman2player) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity.finish();
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