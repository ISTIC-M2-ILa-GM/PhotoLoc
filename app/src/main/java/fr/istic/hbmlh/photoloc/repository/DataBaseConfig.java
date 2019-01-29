package fr.istic.hbmlh.photoloc.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fr.istic.hbmlh.photoloc.model.PhotoLoc;

@Database(entities = {PhotoLoc.class}, version = 1)
public abstract class DataBaseConfig extends RoomDatabase {
    public abstract PhotoLocRepository photoLocRepository();
}
