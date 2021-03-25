package com.example.suneapp.unittest;

import android.content.Context;

public class ResourceCompare {

    public boolean isEqual(Context context, int resourceId, String resource) {
        return context.getString(resourceId).equals(resource);
    }
}
