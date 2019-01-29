package fr.istic.hbmlh.photoloc.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "photo_loc")
public class PhotoLoc {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "file_path")
    private String filePath;

    private Long longitude;

    private Long latitude;

    private ZonedDateTime date;
}
