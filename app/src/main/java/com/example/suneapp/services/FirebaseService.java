package com.example.suneapp.services;

import com.example.suneapp.model.LogApplicationDocument;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.UUID;

public class FirebaseService {
    private static final String TAG = "FirebaseService";
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;

    public FirebaseService() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<QuerySnapshot> getLogs() {
        return db.collection("logs").whereEqualTo("owner", mAuth.getCurrentUser().getUid()).get();
    }

    public Task<AuthResult> signIn(String email, String password) {
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public Task<Void> addLog(List<String> properties) {
        LogApplicationDocument logApplication = new LogApplicationDocument(
                UUID.randomUUID().toString(),
                getUser().getUid(),
                Timestamp.now(),
                properties
        );
        return db.collection("logs").document().set(logApplication);
    }

    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }
}
