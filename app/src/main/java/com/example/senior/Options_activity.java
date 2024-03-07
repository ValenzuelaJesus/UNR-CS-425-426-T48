package com.example.senior;
import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Button;

import android.content.Intent;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.core.content.ContextCompat;

import androidx.camera.core.Camera;
import androidx.camera.view.PreviewView;
import androidx.camera.lifecycle.ProcessCameraProvider;

import androidx.lifecycle.LifecycleOwner;
import com.example.senior.databinding.ActivityOptionsBinding;

import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class Options_activity extends AppCompatActivity {

    private ActivityResultLauncher<String[]> activityResultLauncher;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    // Camera-related constants
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA};

    private ActivityOptionsBinding binding;

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String COLOR_BLINDNESS_MODE_KEY = "colorBlindnessMode";
    private int colorBlindnessMode = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        binding = ActivityOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Restore the selected color blindness mode from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        colorBlindnessMode = prefs.getInt(COLOR_BLINDNESS_MODE_KEY, 0);
        applyColorBlindMode(colorBlindnessMode);


        binding.exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Options_activity.this, MainMenu.class);
                startActivity(i);
            }
        });

        binding.colorblindbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle color blindness mode
                toggleColorBlindMode();

                // Update SharedPreferences with the new color blindness mode
                SharedPreferences preferences = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("colorBlindnessMode", colorBlindnessMode);
                editor.apply();

                // Apply the color blindness mode immediately after updating SharedPreferences
                applyColorBlindMode(colorBlindnessMode);
            }
        });

        binding.resetoptionsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorBlindnessMode = 0;

                // Update SharedPreferences with the new color blindness mode
                SharedPreferences preferences = getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("colorBlindnessMode", colorBlindnessMode);
                editor.apply();

                // Apply the color blindness mode immediately after updating SharedPreferences
                applyColorBlindMode(colorBlindnessMode);
            }
        });



    }
    private void toggleColorBlindMode() {
        // Cycle through the different types of color blindness
        colorBlindnessMode = (colorBlindnessMode + 1) % 4;
    }

    private void applyColorBlindMode(int colorBlindnessMode) {
        // Apply color blindness filter
        ColorBlind.applyColorBlindMode(getWindow().getDecorView().getRootView(), colorBlindnessMode);
    }
}