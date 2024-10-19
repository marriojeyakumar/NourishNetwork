package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VolunteerSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_signup);
    }

    public void continueSignup(View view) {
        Intent intent = new Intent(this, VolunteerDetailedSignupActivity.class);
        startActivity(intent);
    }

    public void backToLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), VolunteerLoginActivity.class);
        startActivity(intent);
    }
}