package fr.istic.hbmlh.photoloc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.istic.hbmlh.photoloc.model.PhotoLoc;
import fr.istic.hbmlh.photoloc.repository.PhotoLocRepository;
import fr.istic.hbmlh.photoloc.repository.impl.RepositoriesImpl;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private PhotoLocRepository photoLocRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        photoLocRepository = new RepositoriesImpl(this).getPhotoLocRepository();
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        photoLocRepository.findAll().observe(this, photoLocs -> photoLocs.forEach(this::addMarker));
    }

    private void addMarker(PhotoLoc photoLoc) {
        if (photoLoc == null || photoLoc.getLatitude() == null || photoLoc.getLongitude() == null) {
            return;
        }
        LatLng latLng = new LatLng(photoLoc.getLatitude(), photoLoc.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(photoLoc.getFilePath()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
