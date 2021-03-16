package com.example.suneapp.model;

public class Application {
    private final String appID;
    private final String appName;
    private final String packageName;

    public Application(String appID, String appName, String packageName) {
        this.appID = appID;
        this.appName = appName;
        this.packageName = packageName;
    }

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
