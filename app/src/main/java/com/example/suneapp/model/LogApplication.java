package com.example.suneapp.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class LogApplication {
    private final String id;
    private final Date date;

    public LogApplication(String id, Date date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + getId() + "/ DATE: " + getDate();
    }
}
