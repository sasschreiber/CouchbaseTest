package com.thm.sas.couchbasetest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import com.couchbase.lite.*;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.util.Log;
import com.thm.sas.couchbasetest.domain.model.Card;
import com.thm.sas.couchbasetest.domain.repository.CardRepository;


public class MainActivity extends ActionBarActivity {

    final String TAG = "HelloWorld";
    CardRepository cardRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "------------------- Begin Hello World App");
        cardRepository = new CardRepository(new AndroidContext(this));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doCouchbaseThings();

        Log.d(TAG, "-------------------- End Hello World App");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    protected void doCouchbaseThings() {
        Map<String, Object> docContent = new HashMap<String, Object>();
        docContent.put("frontText", "This is the front of the card");
        docContent.put("backText", "This is the back of the card");

        cardRepository.add(docContent);

        TextView textView = (TextView) findViewById(R.id.textView2);

        ArrayList<Card> cards = cardRepository.findAll();
        for (Card card : cards) {
            textView.append(card.getFrontText() + " / ");
            textView.append(card.getBackText());
            textView.append(".........");

        }

    }
}
