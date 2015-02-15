package com.thm.sas.couchbasetest.domain.repository;

import com.couchbase.lite.Document;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;
import com.thm.sas.couchbasetest.domain.mapper.MapperFactory;
import com.thm.sas.couchbasetest.domain.mapper.MapperInterface;
import com.thm.sas.couchbasetest.domain.model.Card;

import java.util.ArrayList;
import java.util.Iterator;

public class CardRepository extends CouchbaseRepository {
    public CardRepository(AndroidContext androidContext) {
        super(androidContext);
    }

    public ArrayList<Card> findAll() {
        ArrayList<Card> cards = new ArrayList<Card>();
        QueryEnumerator result = findAllRaw();

        for (Iterator<QueryRow> it = result; it.hasNext(); ) {

            QueryRow row = it.next();

            if (row.getConflictingRevisions().size() > 0) {
                Log.w(TAG, "Conflict in document: %s", row.getDocumentId());
            }
            MapperInterface mapper = MapperFactory.getMapper("Card");
            Card card = (Card) mapper.mapDocumentToModel(row.getDocument());
            cards.add(card);


        }
        return cards;
    }
}
