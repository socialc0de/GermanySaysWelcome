package com.github.socialc0de.gsw.customClasses.api;

import java.util.ArrayList;
import java.util.List;

public class FaqEntry {

    private Integer id;
    private String created;
    private Integer county;
    private List<Integer> audiences = new ArrayList<Integer>();
    private List<Integer> categories = new ArrayList<Integer>();
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
