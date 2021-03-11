package com.example.suneapp.services;

import com.example.suneapp.model.LogApplication;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseService {
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;

    private static final String TAG = "FirebaseService";

    public FirebaseService() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<QuerySnapshot> getLogs() {
        return db.collection("logs").get();
    }

    public Task<AuthResult> signIn(String email, String password) {
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }
}
