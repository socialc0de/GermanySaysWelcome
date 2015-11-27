package com.github.socialc0de.gsw.customClasses;

/**
 * Created by patricebecker on 25/11/15.
 */
public class MapItem {
    private double latitude, longitude;
    private String title;
    private String description;
    private int resource;

    public MapItem(double latitude, double longitude, String title, String description, int resource){
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
        this.resource = resource;
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

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}
