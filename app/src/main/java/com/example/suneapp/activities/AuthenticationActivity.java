package com.example.suneapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.suneapp.R;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }

    public void authenticateUser(View view) {
        EditText email = findViewById(R.id.loginInput);
        EditText password = findViewById(R.id.passwordInput);

        String emailAnswer = email.getText().toString();
        String passwordAnswer = password.getText().toString();

        if (emailAnswer.isEmpty() || passwordAnswer.isEmpty())
            Toast.makeText(getApplicationContext(),
                    "Please enter an email and password !", Toast.LENGTH_SHORT).show();

        if (!emailAnswer.matches("^(.+)@(.+)$") ||
                !passwordAnswer
                        .matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{6,20}$"))
            Toast.makeText(getApplicationContext(),
                    "Please enter a valid email and password !", Toast.LENGTH_SHORT).show();

        // call Firebase authentication service
        // redirect to LogsActivity
        startActivity(new Intent(this, LogsActivity.class));

    }
}