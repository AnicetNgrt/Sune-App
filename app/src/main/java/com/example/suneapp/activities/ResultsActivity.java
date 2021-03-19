package com.example.suneapp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suneapp.R;
import com.example.suneapp.adapter.LogsAdapter;
import com.example.suneapp.model.LogApplicationDocument;
import com.example.suneapp.services.FirebaseService;
import com.example.suneapp.utils.shelltest.ShellCommandExecutor;

import java.util.List;

public class ResultsActivity extends AppCompatActivity implements LogsAdapter.OnLogListener {
    private static final String TAG = "LogsActivity";
    private FirebaseService firebaseService;
    private RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        firebaseService = new FirebaseService();

        recyclerView = findViewById(R.id.logs_recyclerView);
        loadData();
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @Override
    public void onLogClicked(int position) {
        LogsAdapter logsAdapter = (LogsAdapter) recyclerView.getAdapter();
        LogApplicationDocument logApplicationDocument = logsAdapter.getData().get(position);

        Intent details = new Intent(this, GetPropResultViewActivity.class);
        details.putExtra("logApplication", logApplicationDocument.toLogApplication());
        startActivity(details);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadData() {
        firebaseService.getLogs()
                .addOnSuccessListener(task -> recyclerView
                        .setAdapter(new LogsAdapter(
                                task.toObjects(LogApplicationDocument.class),
                                this)));
    }

    public void runPropertiesAnalyzer(View view) {
        List<String> results = ShellCommandExecutor.execute("getprop");

        this.firebaseService.addLog(results)
                .addOnSuccessListener(task -> {
                    Toast.makeText(getApplicationContext(),
                            "Phone properties have been analyzed successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(task -> {
                    Toast.makeText(getApplicationContext(),
                            "An error occurred", Toast.LENGTH_SHORT).show();
                });
    }
}