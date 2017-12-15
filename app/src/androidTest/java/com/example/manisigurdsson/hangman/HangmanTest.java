package com.example.manisigurdsson.hangman;

import android.app.Activity;
import android.content.Intent;
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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HangmanTest {

    private static final String key = "message";

    @Rule
    public ActivityTestRule<Hangman> mActivity =
            new ActivityTestRule<>(Hangman.class, true, false);

    private Result activity;

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
    public void setUp() throws Exception {

        when(mockDB.getReference()).thenReturn(mockRef);
        when(mockRef.child("users")).thenReturn(mockRefChilds);
        when(mockRefChilds.child(any(String.class))).thenReturn(mockRefChilds2);
        when(mockRefChilds2.setValue(any(User.class))).thenReturn(null);
        when(mockUser.getName()).thenReturn("name");
        when(mockSnap.getValue(User.class)).thenReturn(mockUser);
        when(mockUser.getRubies()).thenReturn(123);
        Hangman.setInstance(mockDB);
        mActivity.launchActivity(new Intent());
        noSleep(mActivity.getActivity());
    }

    @After
    public void tearDown() { }

    /* runna ekki offline, virka fínt online
    @Test
    public void testButtonA(){
        ViewInteraction btn = onView(withId(R.id.btn_a));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonAA(){
        ViewInteraction btn = onView(withId(R.id.btn_aa));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonB(){
        ViewInteraction btn = onView(withId(R.id.btn_b));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonC(){
        ViewInteraction btn = onView(withId(R.id.btn_c));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonD(){
        ViewInteraction btn = onView(withId(R.id.btn_d));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }
    @Test
    public void testButtonDD(){
        ViewInteraction btn = onView(withId(R.id.btn_dd));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonE(){
        ViewInteraction btn = onView(withId(R.id.btn_e));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonEE(){
        ViewInteraction btn = onView(withId(R.id.btn_ee));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonF(){
        ViewInteraction btn = onView(withId(R.id.btn_f));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonG(){
        ViewInteraction btn = onView(withId(R.id.btn_g));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonH(){
        ViewInteraction btn = onView(withId(R.id.btn_h));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonI(){
        ViewInteraction btn = onView(withId(R.id.btn_i));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonII(){
        ViewInteraction btn = onView(withId(R.id.btn_ii));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonJ(){
        ViewInteraction btn = onView(withId(R.id.btn_j));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonK(){
        ViewInteraction btn = onView(withId(R.id.btn_k));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonL(){
        ViewInteraction btn = onView(withId(R.id.btn_l));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonM(){
        ViewInteraction btn = onView(withId(R.id.btn_m));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonN(){
        ViewInteraction btn = onView(withId(R.id.btn_n));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonO(){
        ViewInteraction btn = onView(withId(R.id.btn_o));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonOO(){
        ViewInteraction btn = onView(withId(R.id.btn_oo));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonP(){
        ViewInteraction btn = onView(withId(R.id.btn_p));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonQ(){
        ViewInteraction btn = onView(withId(R.id.btn_q));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonR(){
        ViewInteraction btn = onView(withId(R.id.btn_r));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonS(){
        ViewInteraction btn = onView(withId(R.id.btn_s));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonT(){
        ViewInteraction btn = onView(withId(R.id.btn_t));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonU(){
        ViewInteraction btn = onView(withId(R.id.btn_u));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonUU(){
        ViewInteraction btn = onView(withId(R.id.btn_uu));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonV(){
        ViewInteraction btn = onView(withId(R.id.btn_v));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonW(){
        ViewInteraction btn = onView(withId(R.id.btn_w));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonX(){
        ViewInteraction btn = onView(withId(R.id.btn_x));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonY(){
        ViewInteraction btn = onView(withId(R.id.btn_y));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonYY(){
        ViewInteraction btn = onView(withId(R.id.btn_yy));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonZ(){
        ViewInteraction btn = onView(withId(R.id.btn_z));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonTH(){
        ViewInteraction btn = onView(withId(R.id.btn_th));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonAE(){
        ViewInteraction btn = onView(withId(R.id.btn_ae));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testButtonOOO(){
        ViewInteraction btn = onView(withId(R.id.btn_ooo));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }
*/
    private void noSleep(final Activity activity) {
        Runnable wakeUpDevice = new Runnable() {
            @Override
            public void run() {
                activity.getWindow().addFlags(
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                );
            }
        };
        activity.runOnUiThread(wakeUpDevice);
    }
}