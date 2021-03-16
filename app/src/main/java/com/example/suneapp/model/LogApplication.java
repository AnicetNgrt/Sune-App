package com.example.suneapp.model;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

import java.util.List;
import java.util.UUID;

public class LogApplication {
    private final String id;
    private final String owner;
    private final Timestamp timestamp;

    private final List<Application> data;

    public LogApplication(String owner, Timestamp timestamp, List<Application> data) {
        this.id = UUID.randomUUID().toString();
        this.owner = owner;
        this.timestamp = timestamp;
        this.data = data;
    }

    public LogApplication(String id, String owner, Timestamp timestamp, List<Application> data) {
        this.id = id;
        this.owner = owner;
        this.timestamp = timestamp;
        this.data = data;
    }

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
}
