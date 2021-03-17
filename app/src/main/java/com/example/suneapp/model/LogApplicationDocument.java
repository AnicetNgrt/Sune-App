package com.example.suneapp.model;

import com.google.firebase.Timestamp;

import java.util.List;

public class LogApplicationDocument {
    private String id;
    private String owner;
    private Timestamp timestamp;
    private List<Application> data;

    public LogApplicationDocument(){}

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "LogApplication{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }

    public List<Application> getData() {
        return data;
    }

    public LogApplication toLogApplication () {
        return new LogApplication(getId(), getOwner(), getTimestamp().toDate(), getData());
    }
}
