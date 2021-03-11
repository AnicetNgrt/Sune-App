package com.example.suneapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suneapp.R;
import com.example.suneapp.adapter.LogsAdapter;
import com.example.suneapp.model.LogApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class LogsActivity extends AppCompatActivity implements LogsAdapter.OnLogListener {
    private List<LogApplication> applicationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        RecyclerView recyclerView = findViewById(R.id.logs_recyclerView);
        applicationList = new ArrayList<>(Arrays.asList(
                new LogApplication("123RERETE", Calendar.getInstance().getTime()),
                new LogApplication("46475GDHHD", Calendar.getInstance().getTime())
        ));

        recyclerView.setAdapter(new LogsAdapter(applicationList, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    @Override
    public void onLogClicked(int position) {
        LogApplication logApplication = applicationList.get(position);
        Log.d("[LOG APPLICATION]", logApplication.toString());
        Intent details = new Intent(this, ApplicationTreeViewActivity.class);
        details.putExtra("logID", logApplication.getId());
        startActivity(details);
    }
}