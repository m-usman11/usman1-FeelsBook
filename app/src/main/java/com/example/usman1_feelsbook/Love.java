package com.example.usman1_feelsbook;

/**
 * Love feeling: subclass of Emotion
 */
public class Love extends Emotion {
    public Love(String comment) {
        super(comment);
    }

    public int getImage() {
        return R.drawable.love;
    }
}
