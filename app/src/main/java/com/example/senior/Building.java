package com.example.senior;

public class Building {
    private String name;
    private double latitude;
    private double longitude;
    private String Building_code;


    public Building(String name, String code,  double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.Building_code = code;

    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getBuildingCode() {
        return Building_code;
    }

    public Building[] getClosestBuildings(){
        Building[] closestBuildings = new Building[4];
        closestBuildings[0] = new Building("William N. Pennington Building","WPEB",39.53994709346304, -119.81204368554893);
        closestBuildings[1] = new Building("Davidson Math and Science","DMSC", 39.539065822167, -119.81230638240348);
        closestBuildings[2] = new Building("Palmer Engineering", "PE", 39.53952683899103, -119.81289435810741);
        closestBuildings[3] = new Building("Harry Reid Engineering Lab", "HREL",39.54052509682244, -119.81316407287733);
        return closestBuildings;

        //as
    }
}

