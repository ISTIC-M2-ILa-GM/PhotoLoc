package fr.istic.hbmlh.photoloc.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

public class Repositories {

    private static final String PHOTO_LOC_DB_NAME = "photo_loc";

    private DataBaseConfig dataBaseConfig;

    public Repositories(Context context) {
        dataBaseConfig = Room.databaseBuilder(context, DataBaseConfig.class, PHOTO_LOC_DB_NAME).build();
    }

    public PhotoLocRepository getPhotoLocRepository() {
        return dataBaseConfig.photoLocRepository();
    }
}
