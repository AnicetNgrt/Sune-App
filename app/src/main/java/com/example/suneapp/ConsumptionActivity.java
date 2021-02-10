package com.example.suneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ConsumptionActivity extends AppCompatActivity {

    private FloatingActionButton fabBackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        fabBackToHome = findViewById(R.id.fab_back_home);
    }

    public void returnToHome(View view) {
        Intent homeIntent =  new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }
}