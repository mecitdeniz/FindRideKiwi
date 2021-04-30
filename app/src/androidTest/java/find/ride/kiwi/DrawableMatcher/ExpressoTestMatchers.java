package find.ride.kiwi.DrawableMatcher;

import android.view.View;
import org.hamcrest.Matcher;

public class ExpressoTestMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }
}


