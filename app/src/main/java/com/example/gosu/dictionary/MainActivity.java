package com.example.gosu.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle   savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    Intent intent;
    public void openNumbers(View view) {
        intent = new Intent(this, NumbersActivity.class);
        startActivity(intent);
    }

    public void openFamily(View view) {
        intent = new Intent(this, FamilyActivity.class);
        startActivity(intent);
    }

    public void openPhrases(View view) {
        intent = new Intent(this, PhrasesActivity.class);
        startActivity(intent);
    }

    public void openColors(View view) {
        intent = new Intent(this, ColorsActivity.class);
        startActivity(intent);
    }
}