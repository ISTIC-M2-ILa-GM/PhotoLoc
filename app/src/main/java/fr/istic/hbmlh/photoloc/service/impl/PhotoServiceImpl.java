package fr.istic.hbmlh.photoloc.service.impl;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.istic.hbmlh.photoloc.exception.PhotoLocException;
import fr.istic.hbmlh.photoloc.manager.HttpRequest;
import fr.istic.hbmlh.photoloc.model.PhotoLoc;
import fr.istic.hbmlh.photoloc.repository.PhotoLocRepository;
import fr.istic.hbmlh.photoloc.service.LocationService;
import fr.istic.hbmlh.photoloc.service.PhotoService;

import static fr.istic.hbmlh.photoloc.exception.PhotoLocException.MESSAGE;

public class PhotoServiceImpl implements PhotoService {

    private static final String FILE_NAME = "JPEG_%s_";

    public static final int REQUEST_TAKE_PHOTO = 1;

    private HttpRequest httpRequest;

    private String lastPicturePath;

    private PhotoLocRepository photoLocRepository;

    private LocationService locationService;

    public PhotoServiceImpl(PhotoLocRepository photoLocRepository, LocationService locationService, HttpRequest httpRequest) {
        this.photoLocRepository = photoLocRepository;
        this.locationService = locationService;
        this.httpRequest = httpRequest;
    }

    @Override
    public void takePicture(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            try {
                File photoFile = createImageFile(activity);
                lastPicturePath = photoFile.getPath();
                Uri photoURI = FileProvider.getUriForFile(activity, "fr.istic.hbmlh.photoloc.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private File createImageFile(Activity activity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(String.format(FILE_NAME, timeStamp), ".jpg", storageDir);
    }

    @Override
    public void saveLastPhoto() {
        if (lastPicturePath == null) {
            throw new PhotoLocException(String.format(MESSAGE, "PhotoService", "no last picture found"));
        }
        PhotoLoc photoLoc = new PhotoLoc();
        photoLoc.setFilePath(lastPicturePath);
        photoLoc.setDate(new Date());
        Location locToSet = this.locationService.getUserLocation();
        if (locToSet != null) {
            photoLoc.setLatitude(locToSet.getLatitude());
            photoLoc.setLongitude(locToSet.getLongitude());
            // TODO
//            httpRequest.getAdressFromLocation(locToSet, data -> {
//                photoLoc.setAddress(data.formatted_address);
//            });
        }
        AsyncTask.execute(() -> photoLocRepository.insert(photoLoc));
        lastPicturePath = null;
    }
}
