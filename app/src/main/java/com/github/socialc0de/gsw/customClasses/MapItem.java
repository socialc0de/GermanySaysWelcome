package com.github.socialc0de.gsw.customClasses;

/**
 * Created by patricebecker on 25/11/15.
 */
public class MapItem {
    private double latitude, longitude;
    private String title;
    private String description;

    public MapItem(double latitude, double longitude, String title, String description){
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Titel: "+title+" Description: "+description+" Latitude: "+latitude+" Longitude: "+longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
