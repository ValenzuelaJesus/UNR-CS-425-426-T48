package com.example.senior;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.example.senior.databinding.ActivityMoreInfoBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class MoreInfo extends AppCompatActivity {

    private ActivityResultLauncher<String[]> activityResultLauncher;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    // Camera-related constants
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA};

    private ActivityMoreInfoBinding binding;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String COLOR_BLINDNESS_MODE_KEY = "colorBlindnessMode";

    //Webservice Constants
    private ProgressDialog pd;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    private int colorBlindnessMode = 0;

    private Building [] AllBuildings = new Building[99];
    private DiningOption [] AllDiningOptions = new DiningOption[225];
    private Library [] AllLibraries = new Library[225];
    private Hangoutspots[] AllHangoutspots = new Hangoutspots[255];
    private Restroom[] AllRestrooms = new Restroom[255];
    private VendingMachine[] AllVendingMachines = new VendingMachine[255];
    private Resource[] AllResources = new Resource[255];
    private Store[] AllStores = new Store[255];
    private Lab[] AllLabs = new Lab[255];
    private Elevator[] AllElevators = new Elevator[255];
    private Staircase[] AllStaircases = new Staircase[255];
    private SpecialFeature[] AllSpecialFeatures = new SpecialFeature[255];
    private TextView buildingName;
    private TextView buildingCode;
    private TextView buildingNumber;
    private TextView hours;
    private TextView resourcesTextView;
    private ListView resourcesListView;
    private TextView restrooms;
    private TextView elevators;
    private TextView staircases;
    private TextView amenitiesTextView;
    private ListView amenitiesListView;
    private TextView diningOptionsTextView;
    private ListView diningOptionsListView;
    private int buildingId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        binding = ActivityMoreInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        colorBlindnessMode = prefs.getInt(COLOR_BLINDNESS_MODE_KEY, 0);
        applyColorBlindMode(colorBlindnessMode);

        String jsonBuildingData = connectToWebService("http://34.41.18.211/webservices/Building/");
        AllBuildings = Building.createBuildingsFromJson(jsonBuildingData);
        //Log.d("WebServiceResult", "String Returned: " +  connectToWebService("http://34.41.18.211/webservices/dining_option/"));
        String jsonDiningData = connectToWebService("http://34.41.18.211/webservices/dining_option/");
        AllDiningOptions = DiningOption.createDiningOptionsFromJson(jsonDiningData);
        String jsonLibraryData = connectToWebService("http://34.41.18.211/webservices/libraries/");
        AllLibraries = Library.createLibrariesFromJson(jsonLibraryData);
        String jsonHangoutspotsData = connectToWebService("http://34.41.18.211/webservices/hangoutspots/");
        AllHangoutspots = Hangoutspots.createHangoutSpotsFromJson(jsonHangoutspotsData);
        String jsonRestroomsData = connectToWebService("http://34.41.18.211/webservices/restrooms/");
        AllRestrooms = Restroom.createRestroomsFromJson(jsonRestroomsData);
        String jsonVendingMachineData = connectToWebService("http://34.41.18.211/webservices/vendingmachines/");
        AllVendingMachines = VendingMachine.createVendingMachinesFromJson(jsonVendingMachineData);
        String jsonResourcesData = connectToWebService("http://34.41.18.211/webservices/resources/");
        AllResources = Resource.createResourcesFromJson(jsonResourcesData);
        String jsonStoreData = connectToWebService("http://34.41.18.211/webservices/store/");
        AllStores = Store.createStoreFromJson(jsonStoreData);
        String jsonLabData = connectToWebService("http://34.41.18.211/webservices/labs/");
        AllLabs = Lab.createLabsFromJson(jsonLabData);
        String jsonElevatorData = connectToWebService("http://34.41.18.211/webservices/elevators/");
        AllElevators = Elevator.createElevatorsFromJson(jsonElevatorData);
        String jsonStaircaseData = connectToWebService("http://34.41.18.211/webservices/staircases/");
        AllStaircases = Staircase.createStaircasesFromJson(jsonStaircaseData);
        String jsonSpecialFeaturesData = connectToWebService("http://34.41.18.211/webservices/special_features/");
        AllSpecialFeatures = SpecialFeature.createSpecialFeaturesFromJson(jsonSpecialFeaturesData);

        buildingName = findViewById(R.id.building_name);
        buildingCode = findViewById(R.id.building_code_textView);
        buildingNumber = (findViewById(R.id.building_number_textView));
        hours = (findViewById(R.id.hours_textView));
        resourcesListView = (findViewById(R.id.resources_listView));
        resourcesTextView = (findViewById(R.id.resources_textView));
        restrooms = (findViewById(R.id.restrooms_textView));
        elevators = (findViewById(R.id.elevators_textView));
        staircases = (findViewById(R.id.staircases_textView));
        amenitiesListView = findViewById(R.id.amenities_listView);
        amenitiesTextView = (findViewById(R.id.amenities_textView));
        diningOptionsListView = findViewById(R.id.dining_options_listView);
        diningOptionsTextView = findViewById(R.id.dining_options_textView);

        buildingId = getIntent().getIntExtra("building_id", -1);

        // Find the building with the matching ID
        Building building = findBuildingById(buildingId);

        // Update the UI elements with the building information
        updateMoreInfo(building);

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

    private Building findBuildingById(int id) {
        for (Building building : AllBuildings) {
            if (building.getId() == id) {
                return building;
            }
        }
        return null; // Return null if no building is found
    }

    @SuppressLint("SetTextI18n")
    private void updateMoreInfo(Building building) {
        if (building != null) {
            buildingName.setText(building.getName());
            buildingCode.setText(building.getBuildingCode());
            buildingNumber.setText(building.getBuildingNum());
            hours.setText(building.getOperatingHours());

            List<String> resourcesList = buildResourcesList(building);
            if (!resourcesList.isEmpty()) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resourcesList);
                resourcesListView.setAdapter(adapter);
                resourcesListView.setVisibility(View.VISIBLE);
                resourcesTextView.setVisibility(View.GONE);
                updateRestroomTitleConstraints();
            } else {
                resourcesTextView.setText("N/A");
                resourcesTextView.setVisibility(View.VISIBLE);
                resourcesListView.setVisibility(View.GONE);
                updateRestroomTitleConstraints();
            }

            Restroom restroom = findRestroomByBuildingId(buildingId);
            if (restroom!= null) {
                restrooms.setText(restroom.getLocation());
            } else {
                restrooms.setText("N/A");
            }

            Elevator elevator = findElevatorByBuildingId(buildingId);
            if (elevator != null) {
                elevators.setText(elevator.getLocation());
            } else {
                elevators.setText("N/A");
            }

            Staircase staircase = findStaircaseByBuildingId(buildingId);
            if (staircase!= null) {
                staircases.setText(staircase.getLocation());
            } else {
                staircases.setText("N/A");
            }

            List<String> amenitiesList = buildAmenitiesList(building);
            if (!amenitiesList.isEmpty()) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, amenitiesList);
                amenitiesListView.setAdapter(adapter);
                amenitiesListView.setVisibility(View.VISIBLE);
                amenitiesTextView.setVisibility(View.GONE);
                updateDiningOptionsTitleConstraints();
            } else {
                amenitiesTextView.setText("N/A");
                amenitiesTextView.setVisibility(View.VISIBLE);
                amenitiesListView.setVisibility(View.GONE);
                updateDiningOptionsTitleConstraints();
            }

            List<String> diningOptionsList = buildDiningOptionsList(building);
            if (!diningOptionsList.isEmpty()) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, diningOptionsList);
                diningOptionsListView.setAdapter(adapter);
                diningOptionsListView.setVisibility(View.VISIBLE);
                diningOptionsTextView.setVisibility(View.GONE);
            } else {
                diningOptionsTextView.setText("N/A");
                diningOptionsTextView.setVisibility(View.VISIBLE);
                diningOptionsListView.setVisibility(View.GONE);
            }
        } else {
            buildingName.setText("Building Not Found");
            buildingCode.setText("N/A");
            buildingNumber.setText("N/A");
            hours.setText("N/A");
            resourcesTextView.setText("N/A");
            restrooms.setText("N/A");
            elevators.setText("N/A");
            staircases.setText("N/A");
            amenitiesTextView.setText("N/A");
            diningOptionsTextView.setText("N/A");
        }
    }


    private Restroom findRestroomByBuildingId(int buildingId) {
        for (Restroom restroom : AllRestrooms) {
            if (restroom.getBuilding() == buildingId) {
                return restroom;
            }
        }
        return null;
    }
    private Elevator findElevatorByBuildingId(int buildingId) {
        for (Elevator elevator : AllElevators) {
            if (elevator.getBuilding() == buildingId) {
                return elevator;
            }
        }
        return null;
    }

    private Staircase findStaircaseByBuildingId(int buildingId) {
        for (Staircase staircase : AllStaircases) {
            if (staircase.getBuilding() == buildingId) {
                return staircase;
            }
        }
        return null;
    }

    private List<String> buildDiningOptionsList(Building building) {
        List<String> diningOptionsList = new ArrayList<>();
        List<DiningOption> diningOptions = findDiningOptionsByBuildingId(building.getId());
        for (DiningOption diningOption : diningOptions) {
            diningOptionsList.add(diningOption.getDescription());
            diningOptionsList.add(diningOption.getLocation());
            diningOptionsList.add(diningOption.getOperatingHours());
            diningOptionsList.add(diningOption.getWeblink());
            diningOptionsList.add(diningOption.getMenulink());
            diningOptionsList.add("");
        }
        return diningOptionsList;
    }

    private List<DiningOption> findDiningOptionsByBuildingId(int buildingId) {
        List<DiningOption> diningOptions = new ArrayList<>();
        for (DiningOption diningOption : AllDiningOptions) {
            if (diningOption.getBuilding() == buildingId) {
                diningOptions.add(diningOption);
            }
        }
        return diningOptions;
    }
    private List<String> buildResourcesList(Building building) {
        List<String> resourcesList = new ArrayList<>();

        for (Resource resource : AllResources) {
            if (resource.getBuilding() == building.getId()) {
                resourcesList.add(resource.getDescription());
                resourcesList.add(resource.getLocation());
                resourcesList.add(resource.getOperatingHours());
                resourcesList.add(resource.getWeblink());
                resourcesList.add("");
            }
        }

        for (Lab lab : AllLabs) {
            if (lab.getBuilding() == building.getId()) {
                resourcesList.add("Lab: " + lab.getType());
                resourcesList.add(lab.getLocation());
                resourcesList.add("");
            }
        }

        for (Library library : AllLibraries) {
            if (library.getBuilding() == building.getId()) {
                resourcesList.add("Library: " + library.getDescription());
                resourcesList.add(library.getLocation());
                resourcesList.add("");
            }
        }

        return resourcesList;
    }

    private void updateRestroomTitleConstraints() {
        ConstraintLayout constraintLayout = findViewById(R.id.UI);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        if (resourcesListView.getVisibility() == View.VISIBLE) {
            constraintSet.connect(R.id.restrooms_title, ConstraintSet.TOP, R.id.resources_listView, ConstraintSet.BOTTOM, 8);
        } else if (resourcesTextView.getVisibility() == View.VISIBLE) {
            constraintSet.connect(R.id.restrooms_title, ConstraintSet.TOP, R.id.resources_textView, ConstraintSet.BOTTOM, 8);
        }

        constraintSet.applyTo(constraintLayout);
    }

    private List<String> buildAmenitiesList(Building building) {
        List<String> amenitiesList = new ArrayList<>();

        for (Hangoutspots hangoutspots : AllHangoutspots) {
            if (hangoutspots.getBuilding() == building.getId()) {
                amenitiesList.add("Hangout Spots: " + hangoutspots.getDescription());
                amenitiesList.add(hangoutspots.getLocation());
                amenitiesList.add("");
            }
        }

        for (SpecialFeature specialFeature : AllSpecialFeatures) {
            if (specialFeature.getBuilding() == building.getId()) {
                amenitiesList.add("Special Features: " + specialFeature.getDescription());
                amenitiesList.add(specialFeature.getLocation());
                amenitiesList.add("");
            }
        }

        for (Store store : AllStores) {
            if (store.getBuilding() == building.getId()) {
                amenitiesList.add("Stores: " + store.getDescription());
                amenitiesList.add(store.getLocation());
                amenitiesList.add("");
            }
        }

        for (VendingMachine vendingMachine : AllVendingMachines) {
            if (vendingMachine.getBuilding() == building.getId()) {
                amenitiesList.add("Vending Machines: " + vendingMachine.getType());
                amenitiesList.add(vendingMachine.getLocation());
                amenitiesList.add("");
            }
        }

        return amenitiesList;
    }

    private void updateDiningOptionsTitleConstraints() {
        ConstraintLayout constraintLayout = findViewById(R.id.UI);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        if (amenitiesListView.getVisibility() == View.VISIBLE) {
            constraintSet.connect(R.id.dining_options_title, ConstraintSet.TOP, R.id.amenities_listView, ConstraintSet.BOTTOM, 8);
        } else if (amenitiesTextView.getVisibility() == View.VISIBLE) {
            constraintSet.connect(R.id.dining_options_title, ConstraintSet.TOP, R.id.amenities_textView, ConstraintSet.BOTTOM, 8);
        }

        constraintSet.applyTo(constraintLayout);
    }

    private void applyColorBlindMode(int colorBlindnessMode) {
        // Apply color blindness filter
        ColorBlind.applyColorBlindMode(getWindow().getDecorView().getRootView(), colorBlindnessMode);
    }
    private void openWeblink(String weblink) {
        Uri uri = Uri.parse(weblink);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public String connectToWebService(String urlString) {
        final ProgressDialog pd = new ProgressDialog(MoreInfo.this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();

        Future<String> future = executorService.submit(() -> {
            String result = null;
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                result = buffer.toString();
            } catch (MalformedURLException e) {
                Log.e("WebServiceError", "Malformed URL Exception: " + e.getMessage(), e);
            } catch (IOException e) {
                Log.e("WebServiceError", "IO Exception: " + e.getMessage(), e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        });

        try {
            // Get the result from the future. This will block until the callable has completed.
            String result = future.get();
            pd.dismiss();
            return result;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}