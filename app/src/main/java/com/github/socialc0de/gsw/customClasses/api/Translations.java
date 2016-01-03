
package com.github.socialc0de.gsw.customClasses.api;

public class Translations {

    private Ar ar = new Ar();
    private Fr fr = new Fr();
    private En en = new En();
    private De de = new De();

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
    public Translations setAr(Ar ar) {
        this.ar = ar;
        return this;
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
    public Translations setFr(Fr fr) {
        this.fr = fr;
        return this;
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
    public Translations setEn(En en) {
        this.en = en;
        return this;
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

    public void postFormat(){
        ar.postFormat();
        de.postFormat();
        en.postFormat();
        fr.postFormat();

        if(ar.isNull())
            ar = null;
        if(de.isNull())
            de = null;
        if(fr.isNull())
            fr = null;
        if(en.isNull())
            en = null;
    }

}
