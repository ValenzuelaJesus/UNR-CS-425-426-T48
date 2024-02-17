package com.example.senior;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.senior.Building;

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
    private TextView azimuthTextView;
    LocationManager locationManager;
    private SensorManager sensorManager;

    private float[] accelerometerValues = new float[3];
    private float[] magnetometerValues = new float[3];

    private float previousAzimuth = 0.0f;

    private static final float AZIMUTH_THRESHOLD = 10.0f;
    private static final float ALPHA = 0.5f; // Adjust this value to control the amount of smoothing for Azimuth
    private float smoothedAzimuth = 0;

    private int consecutiveRecognitions = 0;
    private static final int REQUIRED_CONSECUTIVE_RECOGNITIONS = 3; // for building recognition accuracy

    private double latitude;
    private double longitude;

    private Building[] closestBuildings = new Building[2];
    private Building LastSuccessfulBuilding;


    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (latitude == 0){
                return;
            }
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometerValues = event.values;
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magnetometerValues = event.values;


                // Calculate azimuth when both accelerometer and magnetometer values are available
                if (accelerometerValues != null && magnetometerValues != null) {
                    float[] rotationMatrix = new float[9];
                    float[] orientationValues = new float[3];

                    // Get the rotation matrix from the accelerometer and magnetometer values
                    SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerValues, magnetometerValues);

                    // only horizontal movement used
                    float[] remappedRotationMatrix = new float[9];
                    SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, remappedRotationMatrix);


                    SensorManager.getOrientation(remappedRotationMatrix, orientationValues);


                    // finds raw azimuth
                    float azimuth = (float) Math.toDegrees(orientationValues[0]);

                    // Ensure azimuth is in the range
                    azimuth = (azimuth + 360) % 360;

                    // Apply a low-pass filter
                    smoothedAzimuth = smoothedAzimuth + ALPHA * (azimuth - smoothedAzimuth);

                    //Only changes the azimuth if a significant change happens
                    if (Math.abs(smoothedAzimuth - previousAzimuth) < 2) {
                        smoothedAzimuth = previousAzimuth;
                    }
                    // Update the UI
                    updateAzimuth(smoothedAzimuth);


                    for (Building building : closestBuildings) {
                    // Calculate bearing to the destination
                        float bearing = calculateBuildingBearing(latitude, longitude, building.getLatitude(), building.getLongitude());


                    // Check if the building is found based on the azimuth and bearing
                        if (Math.abs(smoothedAzimuth - bearing) < AZIMUTH_THRESHOLD) {
                            LastSuccessfulBuilding = building;
                            consecutiveRecognitions++;

                            if (consecutiveRecognitions >= REQUIRED_CONSECUTIVE_RECOGNITIONS) {
//                            // Reset the counter
                                consecutiveRecognitions = 0;
//
//                            // Building is found consistently, perform actions accordingly
                                buildingFound(building);}
                        }
                        else {
                            if(building == LastSuccessfulBuilding){
                                consecutiveRecognitions = 0;
                            }
                        }

                    }
                    previousAzimuth = smoothedAzimuth;
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Handle accuracy changes if needed
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        coordinatesTextView = findViewById(R.id.coordinatesTextView);
        azimuthTextView = findViewById(R.id.azimuthTextView);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

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
        binding.MoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SEARCH BUTTON FUNCTIONALITY WILL GO HERE
                Intent i = new Intent(MainActivity.this,MainMenu.class);
                startActivity(i);
            }

        });
        binding.resetEnvironment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               UndoShowBottomButtons();
            }

        });

        // Initialize the ActivityResultLauncher for requesting permissions
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                this::handlePermissionsResult);

        // Check and request permissions when needed
        checkAndRequestPermissions();
        registerSensorListeners();
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
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    updateCoordinatesTextView(latitude,longitude);
                }
            }
        };

        // Request location updates
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        client.requestLocationUpdates(locationRequest, locationCallback, null);
        GetclosestBuildings();

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

    private void updateCoordinatesTextView(double latitude, double longitude) {
        if (coordinatesTextView != null) {
            String coordinatesText = "Latitude: " + latitude + "\nLongitude: " + longitude;
            coordinatesTextView.setText(coordinatesText);
        }}
    private void registerSensorListeners() {
        // Register the accelerometer and magnetometer sensor listeners
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (accelerometerSensor != null && magnetometerSensor != null) {
            sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(sensorEventListener, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            // Handle the case where one or both sensors are not available
            Toast.makeText(this, "Accelerometer or magnetometer not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void unregisterSensorListeners() {
        // Unregister the sensor listeners to conserve resources
        sensorManager.unregisterListener(sensorEventListener);
    }

    private void updateAzimuth(float azimuth) {
        // Update the UI or perform any other actions with the azimuth
        azimuthTextView.setText("Azimuth: " + azimuth + " degrees");
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            // Update the text view with location coordinates
            updateCoordinatesTextView(latitude, longitude);
        }

        GetclosestBuildings();

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister sensors and location updates when the activity is paused
        unregisterSensorListeners();
    }

    private float calculateBuildingBearing(double userLat, double userLon, double buildingLat, double buildingLon) {
        Location userLocation = new Location("UserLocation");
        userLocation.setLatitude(latitude);
        userLocation.setLongitude(longitude);

        Location buildingLocation = new Location("BuildingLocation");
        buildingLocation.setLatitude(buildingLat);
        buildingLocation.setLongitude(buildingLon);

        // Calculate the bearing from the user's location to the building
        float bearing = userLocation.bearingTo(buildingLocation);

        // Adjust the bearing to be in the range [0, 360)
        if (bearing < 0) {
            bearing += 360;
        }

        return bearing;
    }

    private void buildingFound(Building building) {
        // Building is found, perform actions accordingly
        Toast buildingtoast = Toast.makeText(this, building.getName(), Toast.LENGTH_SHORT);
        buildingtoast.show();
        ShowBottomButtons();



    }
    private void ShowBottomButtons(){
        TextView description = findViewById(R.id.tvTipDescription);
        ImageView icon = findViewById(R.id.infoIcon);

        description.setVisibility(View.GONE);
        icon.setVisibility(View.GONE);

        unregisterSensorListeners();
        binding.resetEnvironment.setVisibility(View.VISIBLE);
        binding.MoreInfo.setVisibility(View.VISIBLE);




    }
    private void UndoShowBottomButtons() {
        TextView description = findViewById(R.id.tvTipDescription);
        ImageView icon = findViewById(R.id.infoIcon);

        description.setVisibility(View.VISIBLE);
        icon.setVisibility(View.VISIBLE);

        registerSensorListeners();
        binding.resetEnvironment.setVisibility(View.INVISIBLE);
        binding.MoreInfo.setVisibility(View.INVISIBLE);
    }
    private void GetclosestBuildings() {
        closestBuildings[0] = new Building("William N. Pennington Building",39.53994709346304, -119.81204368554893);
        closestBuildings[1] = new Building("Davidson Math and Science", 39.539065822167, -119.81230638240348);

    }

}
