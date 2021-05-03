package find.ride.kiwi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import find.ride.kiwi.models.Kiwi;
import find.ride.kiwi.viewmodels.KiwiViewModel;

import static find.ride.kiwi.Constants.LOCATION_PERMISSION_CODE;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private KiwiViewModel kiwiViewModel;
    private List<Kiwi> kiwis;
    private GoogleMap map;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        kiwiViewModel = ViewModelProviders.of(this).get(KiwiViewModel.class);
        kiwiViewModel.init(getContext(), "data.json");
        kiwis = new ArrayList<>();
        kiwis = kiwiViewModel.getKiwis().getValue();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_CODE);

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);

        map.setMyLocationEnabled(true);
        //Add kiwis onto the map
        for (Kiwi kiwi : kiwis) {
            LatLng latLng = new LatLng(kiwi.getLatitude(), kiwi.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title("Kiwi").icon(bitmapDescriptor));
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
        map.moveCamera(CameraUpdateFactory.zoomTo(13.0f));
    }

    public void checkPermission(Activity activity, String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            initMap();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (permissions.length == 1
                    && permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMap();
            }
        }else {
            getActivity().onBackPressed();
        }
    }

    private void initMap() {

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }


}