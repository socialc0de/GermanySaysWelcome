package com.github.socialc0de.gsw.customClasses.api;

/**
 * Created by roman on 06.12.2015.
 */
public class Language {

    public enum LanguageCodes {
        DE, EN, AR, FR
    }

    private String name = "";
    private String description = "";
    private String question = "";
    private String answer = "";
    private String phrase = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

}
