package com.github.socialc0de.gsw.customClasses.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PoiEntry {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("county")
    @Expose
    private Integer county;
    @SerializedName("audiences")
    @Expose
    private List<Integer> audiences = new ArrayList<Integer>();
    @SerializedName("categories")
    @Expose
    private List<Integer> categories = new ArrayList<Integer>();
    @SerializedName("translations")
    @Expose
    private Translations translations;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The created
     */
    public String getCreated() {
        return created;
    }

    /**
     * @param created The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return The county
     */
    public Integer getCounty() {
        return county;
    }

    /**
     * @param county The county
     */
    public void setCounty(Integer county) {
        this.county = county;
    }

    /**
     * @return The audiences
     */
    public List<Integer> getAudiences() {
        return audiences;
    }

    /**
     * @param audiences The audiences
     */
    public void setAudiences(List<Integer> audiences) {
        this.audiences = audiences;
    }

    /**
     * @return The categories
     */
    public List<Integer> getCategories() {
        return categories;
    }

    /**
     * @param categories The categories
     */
    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    /**
     * @return The translations
     */
    public Translations getTranslations() {
        return translations;
    }

    /**
     * @param translations The translations
     */
    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

}
