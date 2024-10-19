package com.example.nourishnet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class VolunteerDetailedSignupActivity extends AppCompatActivity {
    private static final String TAG = "VolunteerSignupActivity";

    private EditText editTextOrganizationType, editTextOrganizationAddress, editTextContactPerson,
            editTextContactEmail, editTextContactPhone, editTextOperatingHours,
            editTextTypesOfFoodAccepted, editTextFoodStorageCapacity, editTextEstimatedFoodDemand,
            editTextTransportationAvailability, editTextWhoFoodServes, editTextDistributionLocations,
            editTextDistributionTiming;

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_detailed_signup);

        // Initialize views
        editTextOrganizationType = findViewById(R.id.editTextOrganizationType);
        editTextOrganizationAddress = findViewById(R.id.editTextOrganizationAddress);
        editTextContactPerson = findViewById(R.id.editTextContactPerson);
        editTextContactEmail = findViewById(R.id.editTextContactEmail);
        editTextContactPhone = findViewById(R.id.editTextContactPhone);
        editTextOperatingHours = findViewById(R.id.editTextOperatingHours);
        editTextTypesOfFoodAccepted = findViewById(R.id.editTextTypesOfFoodAccepted);
        editTextFoodStorageCapacity = findViewById(R.id.editTextFoodStorageCapacity);
        editTextEstimatedFoodDemand = findViewById(R.id.editTextEstimatedFoodDemand);
        editTextTransportationAvailability = findViewById(R.id.editTextTransportationAvailability);
        editTextWhoFoodServes = findViewById(R.id.editTextWhoFoodServes);
        editTextDistributionLocations = findViewById(R.id.editTextDistributionLocations);
        editTextDistributionTiming = findViewById(R.id.editTextDistributionTiming);

        // Initialize Firebase Firestore and Auth
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // You may get these from a previous activity
        Intent prevIntent = getIntent();
        email = prevIntent.getStringExtra("email");
        password = prevIntent.getStringExtra("password");
    }

    // Method to submit form
    public void submitForm(View view) {
        Log.d(TAG, "submitForm: Submitting volunteer data.");

        String organizationType = editTextOrganizationType.getText().toString();
        String organizationAddress = editTextOrganizationAddress.getText().toString();
        String contactPerson = editTextContactPerson.getText().toString();
        String contactEmail = editTextContactEmail.getText().toString();
        String contactPhone = editTextContactPhone.getText().toString();
        String operatingHours = editTextOperatingHours.getText().toString();
        String typesOfFoodAccepted = editTextTypesOfFoodAccepted.getText().toString();
        String foodStorageCapacity = editTextFoodStorageCapacity.getText().toString();
        String estimatedFoodDemand = editTextEstimatedFoodDemand.getText().toString();
        String transportationAvailability = editTextTransportationAvailability.getText().toString();
        String whoFoodServes = editTextWhoFoodServes.getText().toString();
        String distributionLocations = editTextDistributionLocations.getText().toString();
        String distributionTiming = editTextDistributionTiming.getText().toString();

        // Validate form fields
        if (organizationType.isEmpty() || organizationAddress.isEmpty() || contactPerson.isEmpty() ||
                contactEmail.isEmpty() || contactPhone.isEmpty() || operatingHours.isEmpty() ||
                typesOfFoodAccepted.isEmpty() || foodStorageCapacity.isEmpty() ||
                estimatedFoodDemand.isEmpty() || transportationAvailability.isEmpty() ||
                whoFoodServes.isEmpty() || distributionLocations.isEmpty() || distributionTiming.isEmpty()) {
            Toast.makeText(this, "Please fill out all required fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a map to store volunteer data
        Map<String, Object> volunteerData = new HashMap<>();
        volunteerData.put("organizationType", organizationType);
        volunteerData.put("organizationAddress", organizationAddress);
        volunteerData.put("contactPerson", contactPerson);
        volunteerData.put("contactEmail", contactEmail);
        volunteerData.put("contactPhone", contactPhone);
        volunteerData.put("operatingHours", operatingHours);
        volunteerData.put("typesOfFoodAccepted", typesOfFoodAccepted);
        volunteerData.put("foodStorageCapacity", foodStorageCapacity);
        volunteerData.put("estimatedFoodDemand", estimatedFoodDemand);
        volunteerData.put("transportationAvailability", transportationAvailability);
        volunteerData.put("whoFoodServes", whoFoodServes);
        volunteerData.put("distributionLocations", distributionLocations);
        volunteerData.put("distributionTiming", distributionTiming);

        // Store data in Firestore
        db.collection("volunteerOrganizations").document(contactEmail).set(volunteerData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(VolunteerDetailedSignupActivity.this, "Volunteer data saved successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(VolunteerDetailedSignupActivity.this, "Failed to save volunteer data.", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Error saving volunteer data.", task.getException());
                        }
                    }
                });

        // Optionally register user with Firebase Auth
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User registered successfully.");
                        } else {
                            Log.e(TAG, "User registration failed.", task.getException());
                        }
                    }
                });
    }
}
