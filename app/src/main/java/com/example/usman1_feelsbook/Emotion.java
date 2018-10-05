package com.example.usman1_feelsbook;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* Abstract base class for all emotions, including the emotion, comment, and date */
public abstract class Emotion implements Serializable, Comparable<Emotion> {
    // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
    // https://stackoverflow.com/questions/6510724/how-to-convert-java-string-to-date-object
    private String comment;
    private Date date;

    public static int loveCount, joyCount, sadCount, angryCount, surprisedCount, scaredCount;

    // Constructor for feeling entry with comment
    public Emotion(String comment) {
        this.comment = comment;
        this.date = new Date(System.currentTimeMillis());
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(this.date);
    }

    public Date getDateTime() {
        return this.date;
    }

    public void setDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public abstract int getImage();

    @Override
    public int compareTo(Emotion o) {
        return getDateTime().compareTo(o.getDateTime());
    }
}
