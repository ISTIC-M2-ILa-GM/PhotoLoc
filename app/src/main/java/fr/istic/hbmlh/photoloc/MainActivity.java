package fr.istic.hbmlh.photoloc;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import fr.istic.hbmlh.photoloc.service.GPSValues;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;

    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


    }


    public void testGetGPS(View view) {
        System.out.print("oui");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(getApplicationContext(),
                    "ERREUR LORS DE LA RECUPERATION DE LA POSITION", Toast.LENGTH_SHORT).show();
        }
        else {
            Location loc = mFusedLocationClient.getLastLocation().getResult();
//        GPSValues gpsValues = new GPSValues(locationManager);
//        String toShow = "test " + gpsValues.getUserLocation().getLatitude();
            String toShow = "fin + ";
            if(loc != null){
                toShow+="null";
            }
            else {
                toShow += loc.getLatitude();
            }
            Toast.makeText(getApplicationContext(),
                    toShow, Toast.LENGTH_SHORT).show();
        }



    }
}
