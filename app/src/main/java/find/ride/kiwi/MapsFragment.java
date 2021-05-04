package find.ride.kiwi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

import find.ride.kiwi.models.Kiwi;
import find.ride.kiwi.viewmodels.KiwiViewModel;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.getSystemServiceName;
import static androidx.core.content.ContextCompat.startForegroundService;
import static find.ride.kiwi.Constants.LOCATION_PERMISSION_CODE;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private List<Kiwi> kiwis;
    private GoogleMap map;
    private KiwiViewModel kiwiViewModel;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;

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
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
            mapFragment.getMapAsync(this);
        }

        checkPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_CODE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocationServices.getFusedLocationProviderClient(getActivity())
                .removeLocationUpdates(locationCallback);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);

        //Add kiwis onto the map
        for (Kiwi kiwi : kiwis) {
            LatLng latLng = new LatLng(kiwi.getLatitude(), kiwi.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title("Kiwi").icon(bitmapDescriptor));
        }
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
        } else {
            getActivity().onBackPressed();
        }
    }

    @SuppressLint("MissingPermission")
    private void initMap() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult != null && locationResult.getLocations() != null) {
                    double latitude = locationResult.getLastLocation().getLatitude();
                    double longitude = locationResult.getLastLocation().getLongitude();
                    LatLng userLocation = new LatLng(latitude,longitude);

                    map.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                    map.moveCamera(CameraUpdateFactory.zoomTo(13.0f));

                    for (Kiwi kiwi : kiwis) {
                        LatLng latLngKiwi = new LatLng(kiwi.getLatitude(), kiwi.getLongitude());
                        boolean isFoundOne = checkDistance(userLocation,latLngKiwi);

                        if (isFoundOne)
                            Log.d("LOCATION UPDATE :", "Congrulations! You found a kiwi");
                    }

                    Log.d("LOCATION UPDATE FR", "Latitude : " + String.valueOf(latitude) + " Longitude :" + String.valueOf(longitude));
                }
            }
        };

        locationRequest = new LocationRequest();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(getActivity())
                .requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private boolean checkDistance(LatLng from, LatLng to) {
        double distance = SphericalUtil.computeDistanceBetween(from, to);
        if (distance <= 1) return true;
        return false;
    }
}