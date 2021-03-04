package com.example.suneapp.activities;

import android.content.Intent;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.suneapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView batteryPercentage;
    private Button batteryBtn;
    private FloatingActionButton comsumptionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryPercentage = findViewById(R.id.battery_percentage);
        batteryBtn = findViewById(R.id.battery_btn);
        comsumptionBtn = findViewById(R.id.fab_consumption);
    }

    public void loadBatteryStatus(View view) {
        String currentBatteryPercentage = BatteryManager.BATTERY_PROPERTY_CAPACITY + "%";
        batteryPercentage.setText(currentBatteryPercentage);
    }

    public void launchConsumptionActivity(View view) {
        Intent consumptionIntent = new Intent(this, MainActivity.class);
        startActivity(consumptionIntent);
    }
}