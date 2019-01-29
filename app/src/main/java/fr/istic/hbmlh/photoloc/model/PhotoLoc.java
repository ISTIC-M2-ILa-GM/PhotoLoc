package fr.istic.hbmlh.photoloc.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class PhotoLoc {
    private String filePath;
    private Long longitude;
    private Long latitude;
    private ZonedDateTime date;
}
