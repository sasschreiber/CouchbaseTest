package com.thm.sas.couchbasetest.domain.mapper;

/**
 * Created by Sas on 13.02.2015.
 */
public class MapperFactory {
    private static MapperFactory instance = null;

    protected MapperFactory() {
    }

    public static MapperFactory getInstance() {
        if(instance == null) {
            instance = new MapperFactory();
        }
        return instance;
    }

    public static MapperInterface getMapper(String model) {
        if (model.equals("Card")) {
            return new CardMapper();
        }
        return null;
    }
}
