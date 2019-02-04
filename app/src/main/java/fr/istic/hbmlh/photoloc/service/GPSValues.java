package fr.istic.hbmlh.photoloc.service;

import android.location.Location;
import android.location.LocationManager;

public class GPSValues {

    private final LocationManager locationManager;
    private Location currentLocation;

    public GPSValues(LocationManager locationManager) {
        this.locationManager = locationManager;
    }


    public Location getUserLocation() {
        try {


//            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            } else
//                if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//                currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            }

            // default location TODO change that
            if (currentLocation == null) {
                currentLocation = new Location(LocationManager.GPS_PROVIDER);
                currentLocation.setLongitude(2.3488);
                currentLocation.setLatitude(48.8534);
            }
        } catch (SecurityException e) {
            System.out.println("Erreur lors de la récuparation des coordonnées GPS: " + e.getMessage());
            e.printStackTrace();
        }

        return currentLocation;
    }

}
