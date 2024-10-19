package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BusinessSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_signup);
    }

    public void continueSignup(View view) {
        Intent intent = new Intent(this, BusinessDetailedSignupActivity.class);
        startActivity(intent);
    }

    public void backToLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), BusinessLoginActivity.class);
        startActivity(intent);
    }
}