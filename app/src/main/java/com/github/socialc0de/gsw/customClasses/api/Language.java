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

    public Language setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Language setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getQuestion() {
        return question;
    }

    public Language setQuestion(String question) {
        this.question = question;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public Language setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public String getPhrase() {
        return phrase;
    }

    public Language setPhrase(String phrase) {
        this.phrase = phrase;
        return this;
    }

}
