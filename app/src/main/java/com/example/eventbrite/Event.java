package com.example.eventbrite;

public class Event {
    private String title;
    private String data;
    private String author;

    public Event(String title, String data, String author) {
        this.title = title;
        this.data = data;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
