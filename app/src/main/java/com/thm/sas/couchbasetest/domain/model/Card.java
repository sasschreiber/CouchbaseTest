package com.thm.sas.couchbasetest.domain.model;

/**
 * Created by Sas on 13.02.2015.
 */
public class Card {
    private String frontText;

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    private String backText;

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;



}
