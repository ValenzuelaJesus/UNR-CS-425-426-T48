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
}

