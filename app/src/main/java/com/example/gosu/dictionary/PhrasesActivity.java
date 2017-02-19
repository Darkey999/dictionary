package com.example.gosu.dictionary;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    ArrayList<Word> words = new ArrayList<Word>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        words.add(new Word("Gdzie idziesz?", "Where are you going?", 0, 0));
        words.add(new Word("Jak masz na imię?", "What is your name?", 0, 0));
        words.add(new Word("Mam na imię...", "My name is...", 0, 0));
        words.add(new Word("Jak się czujesz?", "How are you feeling?", 0, 0));
        words.add(new Word("Czuję się dobrze", "I’m feeling good.", 0, 0));
        words.add(new Word("Idziesz?", "Are you coming?", 0, 0));
        words.add(new Word("Tak, idę", "Yes, I’m coming.", 0, 0));
        words.add(new Word("Idę", "I’m coming.", 0, 0));
        words.add(new Word("Chodźmy", "Let’s go.", 0, 0));
        words.add(new Word("Chodź tutaj", "Come here.", 0, 0));
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);
        listView = (ListView) findViewById(R.id.numbersRoot);

        listView.setAdapter(itemsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
