package com.thm.sas.couchbasetest.domain.mapper;

import com.couchbase.lite.Document;

/**
 * Created by Sas on 13.02.2015.
 */
public interface MapperInterface {
    public Object mapDocumentToModel(Document document);


}
