package find.ride.kiwi;

import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.core.app.ActivityScenario;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.core.app.ActivityScenario.launch;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4ClassRunner.class)
public class NavigationTest {

    @Test
    public void testNavigationToMapsFragment(){
        ActivityScenario<MainActivity> activityScenario = launch(MainActivity.class);
        onView(withId(R.id.button_first)).perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
    }


    @Test
    public void testBackPressOnMapsFragment(){
        ActivityScenario<MainActivity> activityScenario = launch(MainActivity.class);
        onView(withId(R.id.button_first)).perform(click());

        onView(withId(R.id.map)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.HomeFragment)).check(matches(isDisplayed()));
    }
}
