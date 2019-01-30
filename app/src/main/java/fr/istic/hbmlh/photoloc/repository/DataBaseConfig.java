package fr.istic.hbmlh.photoloc.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import fr.istic.hbmlh.photoloc.model.PhotoLoc;

@Database(entities = {PhotoLoc.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DataBaseConfig extends RoomDatabase {
    public abstract PhotoLocRepository photoLocRepository();
}
