package com.example.senior;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.camera.core.Camera;
import androidx.camera.view.PreviewView;
import androidx.camera.lifecycle.ProcessCameraProvider;

import androidx.lifecycle.LifecycleOwner;
import com.example.senior.databinding.ActivityMainBinding;

import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.android.gms.location.*;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import org.jetbrains.annotations.NotNull;

// Camera preview was implemented with CameraX documentation as reference

public class MainActivity extends AppCompatActivity implements LocationListener {

    private ActivityResultLauncher<String[]> activityResultLauncher;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    // Camera-related constants
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA};
    private ActivityMainBinding binding;
    private TextView coordinatesTextView;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        coordinatesTextView = findViewById(R.id.coordinatesTextView);
        //Location permissions
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},100);
        }else{
             getLocation();
        }

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent i = new Intent(MainActivity.this,Search_activity.class);
               startActivity(i);
            }

        });
        binding.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SEARCH BUTTON FUNCTIONALITY WILL GO HERE
                Intent i = new Intent(MainActivity.this,MainMenu.class);
                startActivity(i);
            }

        });

        // Initialize the ActivityResultLauncher for requesting permissions
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                this::handlePermissionsResult);

        // Check and request permissions when needed
        checkAndRequestPermissions();
    }
    @SuppressLint("MissingPermission")
    private void getLocation(){
        // Create a location request
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1000); // Update interval in milliseconds

        // Create location callback
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult != null && locationResult.getLastLocation() != null) {
                    Location location = locationResult.getLastLocation();
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    updateCoordinatesTextView(latitude,longitude);
                }
            }
        };

        // Request location updates
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        client.requestLocationUpdates(locationRequest, locationCallback, null);


    }

    private void checkAndRequestPermissions() {
        // Check if the required permissions are granted
        Map<String, Boolean> permissionsMap = new HashMap<>();
        for (String permission : REQUIRED_PERMISSIONS) {
            boolean isPermissionGranted =
                    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
            permissionsMap.put(permission, isPermissionGranted);
        }

        // Request permissions if not all are granted
        boolean shouldRequestPermissions = permissionsMap.values().stream().anyMatch(granted -> !granted);

        if (shouldRequestPermissions) {
            // Request permissions using the ActivityResultLauncher
            activityResultLauncher.launch(REQUIRED_PERMISSIONS);
        } else {
            //Permissions Granted,
            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    // catch

                }
            }, ContextCompat.getMainExecutor(this));
        }
    }

    private void handlePermissionsResult(Map<String, Boolean> permissions) {
        // Handle Permission granted/rejected
        boolean permissionGranted = true;
        for (Map.Entry<String, Boolean> entry : permissions.entrySet()) {
            if (entry.getKey().equals(Manifest.permission.CAMERA) && !entry.getValue()) {
                permissionGranted = false;
                break;
            }
        }

        if (!permissionGranted) {
            Toast.makeText(this, "Permission request denied", Toast.LENGTH_SHORT).show();
        }
    }
    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        PreviewView previewView = findViewById(R.id.previewView);

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview);
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();


    }
    private void updateCoordinatesTextView(double latitude, double longitude) {
        if (coordinatesTextView != null) {
            String coordinatesText = "Latitude: " + latitude + "\nLongitude: " + longitude;
            coordinatesTextView.setText(coordinatesText);
        }}
}
