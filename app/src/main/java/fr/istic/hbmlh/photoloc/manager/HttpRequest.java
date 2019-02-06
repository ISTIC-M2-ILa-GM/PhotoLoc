package fr.istic.hbmlh.photoloc.manager;

import android.location.Location;

import fr.istic.hbmlh.photoloc.manager.base.ClientManager;
import fr.istic.hbmlh.photoloc.manager.base.EntityListener;
import fr.istic.hbmlh.photoloc.manager.model.Result;

public class HttpRequest {

    private static final HttpRequest instance = new HttpRequest();

    public static HttpRequest getInstance() {
        return instance;
    }

    private ClientManager<Result> AdressComponents;


    public void getAdressFromLocation(Location location, EntityListener<Result> listener) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +location.getLatitude() +
                ","+location.getLongitude()+"&key=AIzaSyCVazRV2NVNLkfKueg3clIrSQiUJ1x2um4";
        this.AdressComponents.readOne(listener, url );
    }

}
