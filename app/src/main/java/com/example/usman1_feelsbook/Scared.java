package com.example.usman1_feelsbook;

/**
 * Scared feeling: subclass of Emotion
 */
public class Scared extends Emotion {
    public Scared(String comment) {
        super(comment);
    }

    public int getImage() {
        return R.drawable.scared;
    }
}
