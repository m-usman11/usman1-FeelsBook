package com.example.usman1_feelsbook;

/**
 * Joy feeling: subclass of Emotion
 */
public class Joy extends Emotion {
    public Joy(String comment) {
        super(comment);
    }

    public int getImage() {
        return R.drawable.joy;
    }
}
