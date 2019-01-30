package fr.istic.hbmlh.photoloc.service.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.istic.hbmlh.photoloc.service.PhotoService;

import static android.app.Activity.RESULT_OK;

public class PhotoServiceImpl implements PhotoService {

    private static final String FILE_NAME = "JPEG_%s_";

    private static final int REQUEST_TAKE_PHOTO = 1;

    private Uri lastPicturePath;

    @Override
    public void takePicture(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            try {
                File photoFile = createImageFile(activity);
                Uri photoURI = FileProvider.getUriForFile(activity, "fr.istic.hbmlh.photoloc.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void retrieveAndSavePicture(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
            }
        }
    }

    private File createImageFile(Activity activity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(String.format(FILE_NAME, timeStamp), ".jpg", storageDir);
        return image;
    }
}
