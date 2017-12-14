package com.example.manisigurdsson.hangman;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.WindowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

public class Hangman2playerTest {

        @Rule
        public ActivityTestRule<Hangman2player> hangman2playerActivityTestRule =
                new ActivityTestRule<>(Hangman2player.class, true, false);

    private Hangman2player activity;

    @Before
        public void setUp() throws Exception {
            hangman2playerActivityTestRule.launchActivity(new Intent());
            activity = hangman2playerActivityTestRule.getActivity();
            noSleep(activity);
        }

    @After
    public void tearDown() {
    }

    @Test
    public void testTwoPlayerButtonA(){
        ViewInteraction btn = onView(withId(R.id.btn_a));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonAA(){
        ViewInteraction btn = onView(withId(R.id.btn_aa));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonB(){
        ViewInteraction btn = onView(withId(R.id.btn_b));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonC(){
        ViewInteraction btn = onView(withId(R.id.btn_c));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonD(){
        ViewInteraction btn = onView(withId(R.id.btn_d));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }
    @Test
    public void testTwoPlayerButtonDD(){
        ViewInteraction btn = onView(withId(R.id.btn_dd));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonE(){
        ViewInteraction btn = onView(withId(R.id.btn_e));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonEE(){
        ViewInteraction btn = onView(withId(R.id.btn_ee));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonF(){
        ViewInteraction btn = onView(withId(R.id.btn_f));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonG(){
        ViewInteraction btn = onView(withId(R.id.btn_g));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonH(){
        ViewInteraction btn = onView(withId(R.id.btn_h));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonI(){
        ViewInteraction btn = onView(withId(R.id.btn_i));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonII(){
        ViewInteraction btn = onView(withId(R.id.btn_ii));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonJ(){
        ViewInteraction btn = onView(withId(R.id.btn_j));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonK(){
        ViewInteraction btn = onView(withId(R.id.btn_k));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonL(){
        ViewInteraction btn = onView(withId(R.id.btn_l));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonM(){
        ViewInteraction btn = onView(withId(R.id.btn_m));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonN(){
        ViewInteraction btn = onView(withId(R.id.btn_n));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonO(){
        ViewInteraction btn = onView(withId(R.id.btn_o));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonOO(){
        ViewInteraction btn = onView(withId(R.id.btn_oo));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonP(){
        ViewInteraction btn = onView(withId(R.id.btn_p));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonQ(){
        ViewInteraction btn = onView(withId(R.id.btn_q));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonR(){
        ViewInteraction btn = onView(withId(R.id.btn_r));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonS(){
        ViewInteraction btn = onView(withId(R.id.btn_s));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonT(){
        ViewInteraction btn = onView(withId(R.id.btn_t));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonU(){
        ViewInteraction btn = onView(withId(R.id.btn_u));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonUU(){
        ViewInteraction btn = onView(withId(R.id.btn_uu));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonV(){
        ViewInteraction btn = onView(withId(R.id.btn_v));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonW(){
        ViewInteraction btn = onView(withId(R.id.btn_w));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonX(){
        ViewInteraction btn = onView(withId(R.id.btn_x));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonY(){
        ViewInteraction btn = onView(withId(R.id.btn_y));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonYY(){
        ViewInteraction btn = onView(withId(R.id.btn_yy));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonZ(){
        ViewInteraction btn = onView(withId(R.id.btn_z));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonTH(){
        ViewInteraction btn = onView(withId(R.id.btn_th));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonAE(){
        ViewInteraction btn = onView(withId(R.id.btn_ae));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

    @Test
    public void testTwoPlayerButtonOOO(){
        ViewInteraction btn = onView(withId(R.id.btn_ooo));
        btn.check(matches(isClickable()));
        btn.perform(click());
        btn.check(matches(not(isClickable())));
    }

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