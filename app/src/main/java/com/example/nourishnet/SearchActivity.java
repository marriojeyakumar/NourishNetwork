package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SearchActivity extends AppCompatActivity {
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_profile) {
                    navigateToProfile(VolunteerProfileActivity.class);
                    return true;
                }

                return false;
            }
        });
    }

    private void navigateToProfile(Class<?> activityClass) {
        Intent intent = new Intent(this, VolunteerProfileActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }
}