package find.ride.kiwi;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

public class MapUtils {

    public static boolean checkDistance(LatLng from, LatLng to) {
        double distance = SphericalUtil.computeDistanceBetween(from, to);
        if (distance <= 1) return true;
        return false;
    }
}
