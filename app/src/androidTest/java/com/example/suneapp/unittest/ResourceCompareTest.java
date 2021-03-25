package com.example.suneapp.unittest;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.suneapp.R;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceCompareTest {
    private ResourceCompare resourceCompare;

    @Before
    public void init()  {
        resourceCompare = new ResourceCompare();
    }

    @Test
    public void stringResourceSameAsGiven() {
        Context context = ApplicationProvider.getApplicationContext();
        boolean isSame = resourceCompare.isEqual(context, R.string.app_name, "Sune");
        assertTrue(isSame);
    }

    @Test
    public void stringResourceDifferentAsGiven() {
        Context context = ApplicationProvider.getApplicationContext();
        boolean isDifferent = resourceCompare.isEqual(context, R.string.app_name, "SuneApp");
        assertFalse(isDifferent);
    }

}