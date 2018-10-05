package com.example.usman1_feelsbook;

/**
 * Angry feeling: subclass of Emotion
 */
public class Angry extends Emotion {
    public Angry(String comment) {
        super(comment);
    }

    public int getImage() {
        return R.drawable.angry;
    }
}
