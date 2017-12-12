package com.example.manisigurdsson.hangman;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by manisigurdsson on 12/12/17.
 */
@RunWith(AndroidJUnit4.class)
public class MenuTest {

    @Rule
    public ActivityTestRule<Menu> mMenuTestRule =
            new ActivityTestRule<>(Menu.class, true, false);
    private Menu activity;



    @Before
    public void setUp(){

        mMenuTestRule.launchActivity(new Intent());
        activity = mMenuTestRule.getActivity();
        noSleep(activity);
    }
    @After
    public void tearDown(){ }

    @Test
    public void testSinglePlayer(){
        ViewInteraction btn = onView(withId(R.id.singlePlayerID));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(DifficultySettings.class.getName(), null, false);

        btn.perform(click());


        DifficultySettings nextActivity = (DifficultySettings) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }
    @Test
    public void testTwoPlayer(){
        ViewInteraction btn = onView(withId(R.id.twoPlayerID));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(TwoPlayerOptions.class.getName(), null, false);

        btn.perform(click());


        TwoPlayerOptions nextActivity = (TwoPlayerOptions) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }
    @Test
    public void testOptions(){
        ViewInteraction btn = onView(withId(R.id.optionsID));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Options.class.getName(), null, false);

        btn.perform(click());


        Options nextActivity = (Options) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }
    @Test
    public void testHighscores(){
        ViewInteraction btn = onView(withId(R.id.highscoreID));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(HighScoreActivity.class.getName(), null, false);

        btn.perform(click());


        HighScoreActivity nextActivity = (HighScoreActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }
    @Test
    public void testStats(){
        ViewInteraction btn = onView(withId(R.id.statsID));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(StatsActivity.class.getName(), null, false);

        btn.perform(click());


        StatsActivity nextActivity = (StatsActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
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