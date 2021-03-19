package com.example.suneapp.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LogApplication implements Serializable {
    private final String id;
    private final String owner;
    private final Date timestamp;
    private final List<String> data;

    public LogApplication(String id, String owner, Date timestamp, List<String> data) {
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

    public Date getTimestamp() {
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

    public List<String> getData() {
        return data;
    }
}
