package com.example.gosu.dictionary;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    ArrayList<Word> words = new ArrayList<Word>();
    ListView listView;
    MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    int result;
    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (mediaPlayer != null) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    mediaPlayer.pause();
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    mediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    releasePlayer();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        words.add(new Word("tata", "father", R.drawable.family_father, R.raw.father));
        words.add(new Word("mama", "mother", R.drawable.family_mother, R.raw.mother));
        words.add(new Word("syn", "son", R.drawable.family_son, R.raw.son));
        words.add(new Word("córka", "daughter", R.drawable.family_daughter, R.raw.daughter));
        words.add(new Word("starszy brat", "older brother", R.drawable.family_older_brother, R.raw.brother));
        words.add(new Word("młodszy brat", "younger brother", R.drawable.family_younger_brother, R.raw.brother));
        words.add(new Word("starsza siostra", "older sister", R.drawable.family_older_sister, R.raw.sister));
        words.add(new Word("młodsza siostra", "younger sister", R.drawable.family_younger_sister, R.raw.sister));
        words.add(new Word("babcia", "grandmother", R.drawable.family_grandmother, R.raw.grandmother));
        words.add(new Word("dziadek", "grandfather", R.drawable.family_grandfather, R.raw.grandfather));
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_family);
        listView = (ListView) findViewById(R.id.numbersRoot);

        assert listView != null;
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releasePlayer();
                Word word = words.get(i);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioId());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releasePlayer();
                        }
                    });
                }
            }
        });
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

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            releasePlayer();
        }
    }

    public void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
