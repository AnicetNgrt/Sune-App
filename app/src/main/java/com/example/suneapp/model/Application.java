package com.example.suneapp.model;

import java.io.Serializable;

public class Application implements Serializable {
    private String appID;
    private String appName;
    private String packageName;

    public Application(){}

    public String getAppID() {
        return appID;
    }

    public String getAppName() {
        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public String toString() {
        return "Application{" +
                "appID='" + appID + '\'' +
                ", appName='" + appName + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
