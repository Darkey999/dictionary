package com.example.gosu.dictionary;

/**
 * Created by Gosu on 2017-02-05.
 */
public class Word {
    private String englishWord;
    private String otherWord;
    private int imageId;
    private int audioId;
    public Word(String englishWord, String otherWord, int imageId, int audioId) {
        this.englishWord = englishWord;
        this.otherWord = otherWord;
        this.imageId = imageId;
        this.audioId = audioId;
    }

    public String getEnglishWord() {
        return englishWord;
    }
    public int getImageId() {
        return imageId;
    }
    public int getAudioId() {
        return audioId;
    }
    public String getOtherWord() {
        return otherWord;
    }
}
