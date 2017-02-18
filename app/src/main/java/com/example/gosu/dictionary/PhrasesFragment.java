package com.example.gosu.dictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {
    ArrayList<Word> words = new ArrayList<Word>();
    ListView listView;

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_words, container, false);

        //adding objects to arrayList
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

        WordAdapter itemsAdapter = new WordAdapter(getContext(), words, R.color.category_phrases);
        listView = (ListView) rootView.findViewById(R.id.numbersRoot);

        listView.setAdapter(itemsAdapter);
        return rootView;
    }

}
