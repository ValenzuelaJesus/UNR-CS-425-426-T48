package com.example.senior;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.example.senior.databinding.ActivityMoreInfoBinding;
import com.google.common.util.concurrent.ListenableFuture;


public class MoreInfo extends AppCompatActivity {

    private ActivityResultLauncher<String[]> activityResultLauncher;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    // Camera-related constants
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA};

    private ActivityMoreInfoBinding binding;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String COLOR_BLINDNESS_MODE_KEY = "colorBlindnessMode";

    private int colorBlindnessMode = 0;
    private TextView buildingName;
    private TextView buildingCode;
    private TextView buildingNumber;
    private TextView hours;
    private TextView resources;
    private TextView restrooms;
    private TextView elevators;
    private TextView staircases;
    private TextView amenities;
    private TextView foodOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        binding = ActivityMoreInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        colorBlindnessMode = prefs.getInt(COLOR_BLINDNESS_MODE_KEY, 0);
        applyColorBlindMode(colorBlindnessMode);

        buildingName = findViewById(R.id.building_name);
        buildingCode = findViewById(R.id.building_code_textView);
        buildingNumber = (findViewById(R.id.building_number_textView));
        hours = (findViewById(R.id.hours_textView));
        resources = (findViewById(R.id.resources_textView));
        restrooms = (findViewById(R.id.restrooms_textView));
        elevators = (findViewById(R.id.elevators_textView));
        staircases = (findViewById(R.id.staircases_textView));
        amenities = (findViewById(R.id.amenities_textView));
        foodOptions = (findViewById(R.id.food_options_textView));




        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity, which will navigate back to the previous activity
                finish();
            }
        });

        binding.mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MoreInfo.this, MainMenu.class);
                startActivity(i);
            }
        });

    }

    private void updateMoreInfo(Building building) {
        buildingName.setText(building.getName());
        buildingCode.setText(building.getBuildingCode());
        buildingNumber.setText(building.getBuildingNum());
    }

    private void applyColorBlindMode(int colorBlindnessMode) {
        // Apply color blindness filter
        ColorBlind.applyColorBlindMode(getWindow().getDecorView().getRootView(), colorBlindnessMode);
    }
}