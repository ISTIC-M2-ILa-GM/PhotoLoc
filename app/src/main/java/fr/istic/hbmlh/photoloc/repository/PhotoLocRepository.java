package fr.istic.hbmlh.photoloc.repository;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import fr.istic.hbmlh.photoloc.model.PhotoLoc;

@Dao
public interface PhotoLocRepository  {

    @Query("SELECT * FROM photo_loc")
    LiveData<List<PhotoLoc>> findAll();

    @Query("SELECT * FROM photo_loc WHERE id LIKE :id LIMIT 1")
    PhotoLoc findById(int id);

    @Insert
    void insert(PhotoLoc photoLoc);

    @Update
    void update(PhotoLoc photoLoc);

    @Delete
    void delete(PhotoLoc photoLoc);
}
