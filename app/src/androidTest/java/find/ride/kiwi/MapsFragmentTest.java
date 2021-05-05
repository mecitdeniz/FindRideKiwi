package find.ride.kiwi;

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;

import static androidx.test.core.app.ActivityScenario.launch;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MapsFragmentTest {


    @Before
    public void setup(){
        ActivityScenario<MainActivity> activityScenario = launch(MainActivity.class);
        onView(withId(R.id.button_first)).perform(click());
    }

    @Test
    public void testIsMapDisplayed(){
        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsDialogDisplayed(){
        onView(withId(R.id.linearLayout_dialog)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsDialogTextDisplayed(){
        onView(withId(R.id.textView_dialog)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsDialogTextCorrect(){
        onView(withId(R.id.textView_dialog)).check(matches(withText(R.string.explanation)));
    }
}