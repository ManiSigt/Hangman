package com.example.manisigurdsson.hangman;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DifficultySettingsTest {
    private static final String key = "message";

    @Rule
    public ActivityTestRule<DifficultySettings> DifficultySettingsTestRule =
            new ActivityTestRule<>(DifficultySettings.class, true, false);

    private DifficultySettings activity;

    @Mock
    private FirebaseDatabase mockDB;

    @Mock
    private DatabaseReference mockRef, mockRefChilds, mockRefChilds2;

    @Mock
    private User mockUser;

    @Mock
    private Query mockQuery;

    @Mock
    private DataSnapshot mockSnap;

    @Before
    public void setUp(){
        when(mockDB.getReference()).thenReturn(mockRef);
        when(mockRef.child("users")).thenReturn(mockRefChilds);
        when(mockRefChilds.child(any(String.class))).thenReturn(mockRefChilds2);
        when(mockRefChilds2.setValue(any(User.class))).thenReturn(null);

        DifficultySettingsTestRule.launchActivity(new Intent());
        activity = DifficultySettingsTestRule.getActivity();
        noSleep(activity);
    }

    @After
    public void tearDown(){ }

    @Test
    public void testButtonEasy(){
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, DifficultySettings.class);
        intent.putExtra("msg", "1");

        ViewInteraction btn = onView(withId(R.id.easyid));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Hangman.class.getName(), null, false);

        btn.perform(click());

        Hangman.setInstance(mockDB);
        Hangman nextActivity = (Hangman) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }

    @Test
    public void testButtonMedium(){
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, DifficultySettings.class);
        intent.putExtra("msg", "2");

        ViewInteraction btn = onView(withId(R.id.mediumid));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Hangman.class.getName(), null, false);

        btn.perform(click());

        Hangman.setInstance(mockDB);
        Hangman nextActivity = (Hangman) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(nextActivity);
        nextActivity .finish();
    }

    @Test
    public void testButtonHard(){
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, DifficultySettings.class);
        intent.putExtra("msg", "3");

        ViewInteraction btn = onView(withId(R.id.hardid));
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(Hangman.class.getName(), null, false);

        btn.perform(click());

        Hangman.setInstance(mockDB);
        Hangman nextActivity = (Hangman) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
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