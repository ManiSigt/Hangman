package com.example.manisigurdsson.hangman;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.WindowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class getTranslationTest {

    private MockWebServer server;
    private final String expected = "Hús köttur sjónvarp";

    //https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20171205T141245Z.709b144b45763084.686d7bb53df800a4fd509d8268db5eb1e5b48594&text=House%20cat%20television&lang=en-is

    @Rule
    public final ActivityTestRule<Hangman> hangman_activity = new ActivityTestRule<Hangman>(
            Hangman.class, true, false);

    @Before
    public void setUp() throws Exception {
        this.server = new MockWebServer();
        this.server.start();

        String jsonBody = "{\"code\":200,\"lang\":\"en-is\",\"text\":[\"Hús köttur sjónvarp\"]}";
        this.server.enqueue(
                new MockResponse()
                    .setResponseCode(200)
                    .setHeader("Cache-Control", "no-store")
                    .setHeader("Connection", "keep-alive")
                    .setHeader("Content-Length","61")
                    .setHeader("Content-Type","application/json; charset=utf-8")
                    .setHeader("Date","Wed, 13 Dec 2017 18:28:30 GMT")
                    .setHeader("Keep-Alive","timeout=120")
                    .setHeader("Server","nginx/1.6.2")
                    .setHeader("X-Content-Type-Options","nosniff")
                    .setBody(jsonBody));
        Hangman.setPath(this.server.url("/").toString());
        hangman_activity.launchActivity(new Intent());
        noSleep(hangman_activity.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        this.server.close();
        Hangman.setPath("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20171" +
        "205T141245Z.709b144b45763084.686d7bb53df800a4fd509d8268db5eb1e5b48594&text=House%20cat%20television&lang=en-is");
    }

    @Test
    public void doInBackground() throws Exception {
        ViewInteraction txt = onView(withId(R.id.hidden));
        txt.check(matches(withText(expected)));
    }

    @Test
    public void onPostExecute() throws Exception {
    }

    private void noSleep(final Hangman hangman) {
        Runnable wakeUpDevice = new Runnable() {
            @Override
            public void run() {
                hangman.getWindow().addFlags(
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                );
            }
        };
        hangman.runOnUiThread(wakeUpDevice);
    }

}