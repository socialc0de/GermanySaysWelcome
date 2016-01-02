
package com.github.socialc0de.gsw.customClasses.api;

import java.util.HashMap;
import java.util.Map;



public class Translations {

    private Ar ar = new Ar();
    private Fr fr = new Fr();
    private En en = new En();
    private De de = new De();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The fr
     */
    public Fr getFr() {
        return fr;
    }

    /**
     * 
     * @param fr
     *     The fr
     */
    public void setFr(Fr fr) {
        this.fr = fr;
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
    public Translations setDe(De de) {
        this.de = de;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
