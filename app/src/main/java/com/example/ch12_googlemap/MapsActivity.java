package com.example.ch12_googlemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.ch12_googlemap.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GroundOverlayOptions videoMark;

    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment
                = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.568256, 126.897240), 15));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                videoMark = new GroundOverlayOptions().image(
                        BitmapDescriptorFactory
                                .fromResource(R.drawable.presence_video_busy))
                        .position(point, 100f, 100f);
                mMap.addGroundOverlay(videoMark);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성 지도");
        menu.add(0, 2, 0, "일반 지도");
        menu.add(0, 3, 0, "월드컵경기장 바로가기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                        37.568256, 126.897240), 15));
                return true;
        }
        return false;
    }
}