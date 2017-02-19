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

public class NumbersActivity extends AppCompatActivity {
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

        words.add(new Word("jeden", "one", R.drawable.number_one, R.raw.one));
        words.add(new Word("dwa", "two", R.drawable.number_two, R.raw.two));
        words.add(new Word("trzy", "three", R.drawable.number_three, R.raw.three));
        words.add(new Word("cztery", "four", R.drawable.number_four, R.raw.four));
        words.add(new Word("pięć", "five", R.drawable.number_five, R.raw.five));
        words.add(new Word("sześć", "six", R.drawable.number_six, R.raw.six));
        words.add(new Word("siedem", "seven", R.drawable.number_seven, R.raw.seven));
        words.add(new Word("osiem", "eight", R.drawable.number_eight, R.raw.eight));
        words.add(new Word("dziewięć", "nine", R.drawable.number_nine, R.raw.nine));
        words.add(new Word("dziesięć", "ten", R.drawable.number_ten, R.raw.ten));
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        listView = (ListView) findViewById(R.id.numbersRoot);

        assert listView != null;
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releasePlayer();
                Word word = words.get(i);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioId());
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

