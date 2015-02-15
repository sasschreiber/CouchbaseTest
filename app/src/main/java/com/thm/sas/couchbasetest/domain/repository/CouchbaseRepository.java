package com.thm.sas.couchbasetest.domain.repository;

import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;

public class CouchbaseRepository {
    Manager manager;
    Database database;

    final String TAG = "CouchbaseRepository";

    protected String databaseName = "thmcards";

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public CouchbaseRepository(AndroidContext androidContext) {
        initializeManager(androidContext);
        findOrCreateDatabase();
        Log.d(TAG, "Finished initializing.");
    }

    protected void initializeManager(AndroidContext androidContext) {
        try {
            manager = new Manager(androidContext, Manager.DEFAULT_OPTIONS);
            //Log.d (TAG, "Manager created");
        } catch (IOException e) {
            Log.e(TAG, "Cannot create manager object");
        }
    }

    protected void findOrCreateDatabase() {
        // create a name for the database and make sure the name is legal
        if (!Manager.isValidDatabaseName(databaseName)) {
            Log.e(TAG, "Bad database name");
            return;
        }
        try {
            database = manager.getDatabase(databaseName);
            //Log.d (TAG, "Database created");
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Cannot get database");
        }
    }

    public QueryEnumerator findAllRaw() {
        Query query = database.createAllDocumentsQuery();
        query.setAllDocsMode(Query.AllDocsMode.ALL_DOCS);
        try {
            QueryEnumerator result = query.run();
            return result;
        } catch(CouchbaseLiteException e) {
            Log.e(TAG, "Error performing findAll");
        }
        return null;
    }


    public ArrayList<Document> findQueryRows() {
        ArrayList<Document> resultList = new ArrayList<Document>();
        // Let's find the documents that have conflicts so we can resolve them:
        Query query = database.createAllDocumentsQuery();
        query.setAllDocsMode(Query.AllDocsMode.ALL_DOCS);
        try {
            QueryEnumerator result = query.run();
            for (Iterator<QueryRow> it = result; it.hasNext(); ) {
                QueryRow row = it.next();
                resultList.add(row.getDocument());
                if (row.getConflictingRevisions().size() > 0) {
                    Log.w(TAG, "Conflict in document: %s", row.getDocumentId());
                }
            }
        } catch(CouchbaseLiteException e) {
            Log.e(TAG, "Error performing findAll");
        }
        return resultList;
    }

    public Document findById(String docID) {
        return database.getDocument(docID);
    }

    public void add(Map<String, Object> documentContent) {
        Document document = database.createDocument();
        try {
            document.putProperties(documentContent);
            Log.d (TAG, "Document written to database named " + databaseName + " with ID = " + document.getId());
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, "Cannot write document to database", e);
        }
    }

}
