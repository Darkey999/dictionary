package com.example.gosu.dictionary;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    public NumbersFragment() {
        // Required empty public constructor
    }

    ArrayList<Word> words = new ArrayList<Word>();
    ListView listView;
    MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    int result;
    //handling focus of the AudioManager
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_words, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        //adding objects to arrayList
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

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        listView = (ListView) rootView.findViewById(R.id.numbersRoot);

        assert listView != null;
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releasePlayer();
                Word word = words.get(i);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioId());
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

        return rootView;
    }

    //releasing mediaPlayer when app is stopped
    @Override
    public void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            releasePlayer();
        }
    }

    //function used for releasing mediaPlayer
    public void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
