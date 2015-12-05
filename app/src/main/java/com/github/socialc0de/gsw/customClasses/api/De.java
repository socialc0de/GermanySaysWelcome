
package com.github.socialc0de.gsw.customClasses.api;

import java.util.HashMap;
import java.util.Map;



public class De {

    private String phrase;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The phrase
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * 
     * @param phrase
     *     The phrase
     */
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
