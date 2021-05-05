package find.ride.kiwi;

import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;
import static androidx.test.core.app.ActivityScenario.launch;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static find.ride.kiwi.DrawableMatcher.ExpressoTestMatchers.withDrawable;

public class HomeFragmentTest {

    @Before
    public void setup(){
        ActivityScenario<MainActivity> activityScenario = launch(MainActivity.class);
    }

    @Test
    public void testIsHomeFragmentInView() {
        onView(withId(R.id.HomeFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsLanguageSpinnerInView(){
        onView(withId(R.id.spinner_language)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsImageViewInView(){
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsImageCorrectInImageView(){
        onView(withId(R.id.imageView)).check(matches(withDrawable(R.drawable.img_home)));
    }

    @Test
    public void testIsButtonInView(){
        onView(withId(R.id.button_first)).check(matches(isDisplayed()));
    }

    @Test
    public void testIsButtonTextCorrect(){
        onView(withId(R.id.button_first)).check(matches(withText(R.string.yes)));
    }

}