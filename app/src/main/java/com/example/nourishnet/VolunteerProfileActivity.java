package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VolunteerProfileActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private String userEmail;

    private TextView textViewOrganizationName, textViewOrganizationType, textViewAddress,
            textViewContactPerson, textViewContactEmail, textViewContactPhoneNumber,
            textViewOperatingHours, textViewTypesOfFoodAccepted, textViewFoodStorageCapacity,
            textViewEstimatedFoodDemand, textViewTransportationAvailability, textViewWhoFoodServes,
            textViewDistributionLocations, textViewDistributionTiming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_profile);

        // Initialize views
        textViewOrganizationName = findViewById(R.id.textViewOrganizationName);
        textViewOrganizationType = findViewById(R.id.textViewOrganizationType);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewContactPerson = findViewById(R.id.textViewContactPerson);
        textViewContactEmail = findViewById(R.id.textViewContactEmail);
        textViewContactPhoneNumber = findViewById(R.id.textViewContactPhoneNumber);
        textViewOperatingHours = findViewById(R.id.textViewOperatingHours);
        textViewTypesOfFoodAccepted = findViewById(R.id.textViewTypesOfFoodAccepted);
        textViewFoodStorageCapacity = findViewById(R.id.textViewFoodStorageCapacity);
        textViewEstimatedFoodDemand = findViewById(R.id.textViewEstimatedFoodDemand);
        textViewTransportationAvailability = findViewById(R.id.textViewTransportationAvailability);
        textViewWhoFoodServes = findViewById(R.id.textViewWhoFoodServes);
        textViewDistributionLocations = findViewById(R.id.textViewDistributionLocations);
        textViewDistributionTiming = findViewById(R.id.textViewDistributionTiming);

        // Get email from previous intent
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Fetch volunteer profile data
        fetchVolunteerProfileData(userEmail);

        // Set up bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_profile) {
                    return true;
                } else if (id == R.id.bottom_search) {
                    navigateToActivity(SearchActivity.class);
                    return true;
                }
                return false;
            }
        });
    }

    private void fetchVolunteerProfileData(String email) {
        db.collection("volunteers").document(email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    // Map fields from the screenshot to the corresponding views
                    textViewContactEmail.setText("Contact Email: " + document.getString("contactEmail"));
                    textViewContactPerson.setText("Contact Person: " + document.getString("contactPerson"));
                    textViewContactPhoneNumber.setText("Contact Phone: " + document.getString("contactPhone"));
                    textViewDistributionLocations.setText("Distribution Locations: " + document.getString("distributionLocations"));
                    textViewDistributionTiming.setText("Distribution Timing: " + document.getString("distributionTiming"));
                    textViewEstimatedFoodDemand.setText("Estimated Food Demand: " + document.getString("estimatedFoodDemand"));
                    textViewFoodStorageCapacity.setText("Food Storage Capacity: " + document.getString("foodStorageCapacity"));
                    textViewOperatingHours.setText("Operating Hours: " + document.getString("operatingHours"));
                    textViewAddress.setText("Organization Address: " + document.getString("organizationAddress"));
                    textViewOrganizationType.setText("Organization Type: " + document.getString("organizationType"));
                    textViewTransportationAvailability.setText("Transportation Availability: " + document.getString("transportationAvailability"));
                    textViewTypesOfFoodAccepted.setText("Types of Food Accepted: " + document.getString("typesOfFoodAccepted"));
                    textViewWhoFoodServes.setText("Who the Food Serves: " + document.getString("whoFoodServes"));
                } else {
                    Toast.makeText(VolunteerProfileActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(VolunteerProfileActivity.this, "Fetch failed: " + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }
}
