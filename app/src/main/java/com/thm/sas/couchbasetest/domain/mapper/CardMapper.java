package com.thm.sas.couchbasetest.domain.mapper;

import com.couchbase.lite.Document;
import com.thm.sas.couchbasetest.domain.model.Card;

/**
 * Created by Sas on 13.02.2015.
 */
public class CardMapper implements MapperInterface {
    public Card mapDocumentToModel(Document document) {
        Card card = new Card();
        card.setId(String.valueOf(document.getId()));
        card.setFrontText(String.valueOf(document.getProperty("frontText")));
        card.setBackText(String.valueOf(document.getProperty("backText")));
        return card;
    }
}
