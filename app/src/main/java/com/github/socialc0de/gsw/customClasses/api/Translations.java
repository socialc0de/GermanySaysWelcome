
package com.github.socialc0de.gsw.customClasses.api;

import java.util.HashMap;
import java.util.Map;

public class Translations {

    private De de;
    private Ar ar;
    private En en;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The de
     */
    public De getDe() {
        return de;
    }

    /**
     * 
     * @param de
     *     The de
     */
    public void setDe(De de) {
        this.de = de;
    }

    /**
     * 
     * @return
     *     The ar
     */
    public Ar getAr() {
        return ar;
    }

    /**
     * 
     * @param ar
     *     The ar
     */
    public void setAr(Ar ar) {
        this.ar = ar;
    }

    /**
     * 
     * @return
     *     The en
     */
    public En getEn() {
        return en;
    }

    /**
     * 
     * @param en
     *     The en
     */
    public void setEn(En en) {
        this.en = en;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
