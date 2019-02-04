package fr.istic.hbmlh.photoloc.repository.impl;

import android.arch.persistence.room.Room;
import android.content.Context;

import fr.istic.hbmlh.photoloc.repository.utils.DataBaseConfig;
import fr.istic.hbmlh.photoloc.repository.PhotoLocRepository;
import fr.istic.hbmlh.photoloc.repository.Repositories;

public class RepositoriesImpl implements Repositories {

    private static final String PHOTO_LOC_DB_NAME = "photo_loc";

    private DataBaseConfig dataBaseConfig;

    public RepositoriesImpl(Context context) {
        dataBaseConfig = Room.databaseBuilder(context, DataBaseConfig.class, PHOTO_LOC_DB_NAME).build();
    }

    public PhotoLocRepository getPhotoLocRepository() {
        return dataBaseConfig.photoLocRepository();
    }
}
