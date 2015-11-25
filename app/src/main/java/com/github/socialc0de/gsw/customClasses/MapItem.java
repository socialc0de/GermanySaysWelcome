package com.github.socialc0de.gsw.customClasses;

/**
 * Created by patricebecker on 25/11/15.
 */
public class MapItem {
    private Long latitude, longitude;
    private String title;
    private String description;

    public MapItem(Long latitude, Long longitude, String title, String description){
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
    }
}
