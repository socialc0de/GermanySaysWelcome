package com.github.socialc0de.gsw.customClasses.api;

/**
 * Created by roman on 06.12.2015.
 */
public class Language {

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

    public void postFormat() {
        if (name.equals(""))
            name = null;
        if (description.equals(""))
            description = null;
        if (question.equals(""))
            question = null;
        if (answer.equals(""))
            answer = null;
        if (phrase.equals(""))
            phrase = null;
    }

    public boolean isNull() {
        return (name == null || name.equals("")) && (description == null || description.equals("")) && (question == null || question.equals("")) && (answer == null || answer.equals("")) && (phrase == null || phrase.equals(""));
    }

    public enum LanguageCodes {
        de, en, ar, fr
    }

}
