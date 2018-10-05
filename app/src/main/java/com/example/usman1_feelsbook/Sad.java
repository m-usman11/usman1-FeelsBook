package com.example.usman1_feelsbook;

/**
 * Sad feeling: subclass of Emotion
 */
public class Sad extends Emotion {
    public Sad(String comment) {
        super(comment);
    }

    public int getImage() {
        return R.drawable.sad;
    }
}
