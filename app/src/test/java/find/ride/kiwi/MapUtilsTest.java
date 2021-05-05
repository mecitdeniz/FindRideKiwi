package find.ride.kiwi;

import com.google.android.gms.maps.model.LatLng;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapUtilsTest {

    @Test
    public void testCheckDistanceReturnsTrue() {
        LatLng latLng = new LatLng(32,32);
        boolean result =  MapUtils.checkDistance(latLng,latLng);
        assertTrue(result);
    }

    @Test
    public void testCheckDistanceReturnsFalse() {
        LatLng from = new LatLng(32,32);
        LatLng to = new LatLng(-32,-32);
        boolean result =  MapUtils.checkDistance(from,to);
        assertFalse(result);
    }
}