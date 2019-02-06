package fr.istic.hbmlh.photoloc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.istic.hbmlh.photoloc.adapter.MapItemAdapter;
import fr.istic.hbmlh.photoloc.model.PhotoLoc;
import fr.istic.hbmlh.photoloc.repository.PhotoLocRepository;
import fr.istic.hbmlh.photoloc.repository.impl.RepositoriesImpl;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String PHOTO_ID = "photo";

    private GoogleMap mMap;

    private PhotoLocRepository photoLocRepository;

    private Integer photoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(PHOTO_ID)) {
            photoId = getIntent().getExtras().getInt(PHOTO_ID);
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        photoLocRepository = new RepositoriesImpl(this).getPhotoLocRepository();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new MapItemAdapter(this));
        photoLocRepository.findAll().observe(this, photoLocs -> {
            if (photoLocs != null) {
                photoLocs.forEach(this::addMarker);
            }
        });
    }

    private void addMarker(PhotoLoc photoLoc) {
        if (photoLoc == null || photoLoc.getLatitude() == null || photoLoc.getLongitude() == null) {
            return;
        }
        LatLng latLng = new LatLng(photoLoc.getLatitude(), photoLoc.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title(photoLoc.getFilePath()));
        if (photoId == null || photoId.equals(photoLoc.getId())) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }
    }
}
