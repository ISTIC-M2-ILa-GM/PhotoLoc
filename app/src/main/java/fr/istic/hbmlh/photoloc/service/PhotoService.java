package fr.istic.hbmlh.photoloc.service;

import android.app.Activity;

public interface PhotoService {

    void takePicture(Activity activity);

    void saveLastPhoto();
}
