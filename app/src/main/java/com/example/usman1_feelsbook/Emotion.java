package com.example.usman1_feelsbook;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Emotion: Abstract base class for all feelings/emotions
 * Emotions are composed of an image, comment, and date
 */

public abstract class Emotion implements Serializable, Comparable<Emotion> {
    private String comment;
    private Date date;

    // Constructor for an emotion, with a comment that MAY be empty
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

    /**
     * @return String representing ISO8601 date
     */
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(this.date);
    }

    /**
     * @return Emotion internal date object
     */
    private Date getDateTime() {
        return this.date;
    }

    /**
     * @param date String to be converted to Date object
     */
    public void setDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return drawable integer value
     */
    public abstract int getImage();

    /**
     * Comparison overloading for sorting by Date
     */
    @Override
    public int compareTo(Emotion o) {
        return getDateTime().compareTo(o.getDateTime());
    }
}
