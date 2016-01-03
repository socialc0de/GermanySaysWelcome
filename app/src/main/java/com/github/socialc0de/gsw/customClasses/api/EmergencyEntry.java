package com.github.socialc0de.gsw.customClasses.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmergencyEntry {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("county")
    @Expose
    private Integer county;
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
     * @return The number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number The number
     */
    public void setNumber(String number) {
        this.number = number;
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
