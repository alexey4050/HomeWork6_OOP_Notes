package model;

import java.util.Date;

public class Note {
    private String id;
    private String title;
    private String text;
    private Date date;

    public Note(String title, String text) {
        date = new Date();
        this.title = title;
        this.text = text;
    }

    public Note(String id, String title, String text, Date date) {
        this(title, text);
        this.id = id;
        this.date = date;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор: %s\nЗаголовок: %s, \nТекст: %s,\nДата: ", id, title, text, date);
    }
}
