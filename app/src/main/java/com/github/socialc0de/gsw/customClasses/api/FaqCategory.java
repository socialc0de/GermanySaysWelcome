
package com.github.socialc0de.gsw.customClasses.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaqCategory {

    private Integer id;
    private List<Translation> translations = new ArrayList<Translation>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The translations
     */
    public List<Translation> getTranslations() {
        return translations;
    }

    /**
     * 
     * @param translations
     *     The translations
     */
    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
