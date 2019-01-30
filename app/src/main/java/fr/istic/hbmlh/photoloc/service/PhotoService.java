package fr.istic.hbmlh.photoloc.service;

import android.app.Activity;
import android.content.Intent;

public interface PhotoService {

    void takePicture(Activity activity);

    void retrieveAndSavePicture(int requestCode, int resultCode, Intent data);
}
