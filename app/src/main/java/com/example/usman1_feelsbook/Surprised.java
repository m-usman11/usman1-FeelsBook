package com.example.usman1_feelsbook;

/**
 * Surprised feeling: subclass of Emotion
 */
public class Surprised extends Emotion {
    public Surprised(String comment) {
        super(comment);
    }

    public int getImage() {
        return R.drawable.surprised;
    }
}
