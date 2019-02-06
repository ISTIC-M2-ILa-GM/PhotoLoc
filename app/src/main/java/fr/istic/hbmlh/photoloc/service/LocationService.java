package fr.istic.hbmlh.photoloc.service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import fr.istic.hbmlh.photoloc.manager.HttpRequest;
import fr.istic.hbmlh.photoloc.model.AdressListener;

public class LocationService {

    private final LocationManager locationManager;
    private final Context context;
    private Location currentLocation;


    public LocationService(Context context) {
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.context = context;
    }


    public Location getUserLocation() {
        try {
            updateGpsCoordinate();
            if (currentLocation == null) {
                Toast.makeText(context,
                        "Aucune coordonnée GPS.", Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (SecurityException e) {
            System.out.println("Erreur lors de la récuparation des coordonnées GPS: " + e.getMessage());
            e.printStackTrace();
        }
        return currentLocation;
    }


    public void updateGpsCoordinate() {
        if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(context,
                    "Erreur : aucune permission pour acceder au GPS.", Toast.LENGTH_SHORT).show();
        } else {
            String locationProvider;
            if(isEmulator()){
                locationProvider = LocationManager.GPS_PROVIDER;
            }
            else{
                locationProvider = LocationManager.NETWORK_PROVIDER;
            }
            Location loc = locationManager.getLastKnownLocation(locationProvider);
            if (loc != null) {
                this.currentLocation = loc;
            }
        }
    }

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public void getAdressFromLocation(Location location, AdressListener adressListener){

        HttpRequest httpRequest = new HttpRequest();
        httpRequest.getAdressFromLocation(location, data -> {
            adressListener.findAddress(data.formatted_address);
        });
    }

}
