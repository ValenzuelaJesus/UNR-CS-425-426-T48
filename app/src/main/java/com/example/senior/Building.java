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
//        closestBuildings[0] = new Building("William N. Pennington","WPEB",39.53994709346304, -119.81204368554893);
//        closestBuildings[1] = new Building("Davidson Math and Science","DMSC", 39.539065822167, -119.81230638240348);
//        closestBuildings[2] = new Building("Palmer Engineering", "PE", 39.53952683899103, -119.81289435810741);
//        closestBuildings[3] = new Building("Harry Reid Engineering Lab", "HREL",39.54052509682244, -119.81316407287733);

        closestBuildings[0] = new Building("Ansari Business", "AB", 39.54007058321954, -119.81470412193153);
        closestBuildings[1] = new Building("Schulich Lecture Hall", "SLH", 39.5408769376099, -119.8145605669878);
        closestBuildings[2] = new Building("Leifson Physics", "LP", 39.5411347920165, -119.81405833921025);
        closestBuildings[3] = new Building("Chemistry Building", "CB", 39.540771252129545, -119.8143333037202);

        return closestBuildings;

        //as
    }
}

