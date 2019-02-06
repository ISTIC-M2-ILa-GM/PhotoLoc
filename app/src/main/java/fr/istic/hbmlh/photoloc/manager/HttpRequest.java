package fr.istic.hbmlh.photoloc.manager;

import android.content.Context;
import android.location.Location;

import fr.istic.hbmlh.photoloc.R;
import fr.istic.hbmlh.photoloc.manager.base.ClientManager;
import fr.istic.hbmlh.photoloc.manager.base.EntityListener;
import fr.istic.hbmlh.photoloc.manager.model.Result;

public class HttpRequest {


    private Context context;

    public HttpRequest(Context context) {
        this.context = context;
    }

    private ClientManager<Result> AdressComponents;


    public void getAdressFromLocation(Location location, EntityListener<Result> listener) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +location.getLatitude() +
                ","+location.getLongitude()+"&key=" + context.getResources().getString(R.string.google_maps_key);
        this.AdressComponents.readOne(listener, url );
    }

}
