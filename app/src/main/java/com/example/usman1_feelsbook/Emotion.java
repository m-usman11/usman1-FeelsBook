package com.example.usman1_feelsbook;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Abstract base class for all emotions, including the emotion, comment, and date */
public abstract class Emotion implements Serializable {
    private String comment;
    private String date;

    // Constructor for feeling entry with comment
    public Emotion(String comment) {
        this.comment = comment;
        Date dateObj = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.date = dateFormat.format(dateObj);
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return this.date;
    }

    public abstract int getImage();

    public void setDate(String date) {
        this.date = date;
    }
}
