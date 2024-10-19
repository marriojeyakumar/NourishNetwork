package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BusinessSignupActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_signup);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    public void continueSignup(View view) {

        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        Intent intent = new Intent(this, BusinessDetailedSignupActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    public void backToLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), BusinessLoginActivity.class);
        startActivity(intent);
    }
}