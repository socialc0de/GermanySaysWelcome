
package com.github.socialc0de.gsw.customClasses.api;

import java.util.HashMap;
import java.util.Map;


public class Translation {

    private String name;
    private String languageCode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The languageCode
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * 
     * @param languageCode
     *     The language_code
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
