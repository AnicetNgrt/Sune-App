package com.example.suneapp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suneapp.R;
import com.example.suneapp.adapter.LogsAdapter;
import com.example.suneapp.model.Application;
import com.example.suneapp.model.LogApplication;
import com.example.suneapp.services.FirebaseService;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.util.stream.Collectors;

public class LogsActivity extends AppCompatActivity implements LogsAdapter.OnLogListener {
    private FirebaseService firebaseService;
    private List<LogApplication> applicationList;
    private static final String TAG = "LogsActivity";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        RecyclerView recyclerView = findViewById(R.id.logs_recyclerView);
        firebaseService = new FirebaseService();
//        applicationList = new ArrayList<>(Arrays.asList(
//                new LogApplication("123RERETE", "owner", Timestamp.now(), new ArrayList<>()),
//                new LogApplication("46475GDHHD", "owner", Timestamp.now(), new ArrayList<>())
//        ));
        applicationList = new ArrayList<>();
        loadData();

        recyclerView.setAdapter(new LogsAdapter(applicationList, this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    @Override
    public void onLogClicked(int position) {
        LogApplication logApplication = applicationList.get(position);
        Log.d(TAG, logApplication.toString());
        Intent details = new Intent(this, ApplicationTreeViewActivity.class);
        details.putExtra("logID", logApplication.getId());
        startActivity(details);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadData() {
        firebaseService.getLogs()
                .addOnSuccessListener(task -> {
                    List<LogApplication> logApplications = task.getDocuments().stream()
                            .map(doc -> {
                                String id = doc.getId();
                               String owner = Objects.requireNonNull(doc.get("owner")).toString();
                               Timestamp timestamp = doc.getTimestamp("timestamp");
                               List<Map<String, Object>> data = (List<Map<String, Object>>) doc.get("data");
                                List<Application> applications = null;
                               if(data != null) {
                                  applications = data.stream().map(app -> {
                                       String appId = Objects.requireNonNull(app.get("appID")).toString();
                                       String appName = Objects.requireNonNull(app.get("appName")).toString();
                                       String packageName = Objects.requireNonNull(app.get("packageName")).toString();
                                       return new Application(appId, appName, packageName);
                                   }).collect(Collectors.toList());
                               }

                               return new LogApplication(id, owner, timestamp, applications);
                            })
                            .collect(Collectors.toList());
                    setApplicationList(logApplications);
                });
    }

    public void setApplicationList(List<LogApplication> applicationList) {
        this.applicationList = applicationList;
    }
}