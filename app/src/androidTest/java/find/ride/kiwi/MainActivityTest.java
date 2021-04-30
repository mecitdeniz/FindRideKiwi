package find.ride.kiwi;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ActivityScenario.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    @Test
    public void testIsActivityInView() {
        ActivityScenario<MainActivity> activityScenario = launch(MainActivity.class);
        onView(withId(R.id.MainActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsNavHostFragmentInView(){
        ActivityScenario<MainActivity> activityScenario = launch(MainActivity.class);
        onView(withId(R.id.nav_host_fragment)).check(matches(isDisplayed()));
    }
}