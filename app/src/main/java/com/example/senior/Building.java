package com.example.senior;

import android.util.Log;

import java.util.Arrays;

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

    public Building[] getClosestBuildings(double userLat, double userLon ,Building [] buildings) {


        Log.d("User Cords", userLat + " "+ userLon);

        // Sorting the buildings by distance

        Arrays.sort(buildings, (b1, b2) -> {
            double distanceToB1 = calculateDistance(userLat, userLon, b1.getLatitude(), b1.getLongitude());
            double distanceToB2 = calculateDistance(userLat, userLon, b2.getLatitude(), b2.getLongitude());
            return Double.compare(distanceToB1, distanceToB2);
        });

        // get the 4 closest buildings
        Building[] closestBuildings = Arrays.copyOfRange(buildings, 0, Math.min(buildings.length, 4));
        for (Building building : buildings) {
            double distance = calculateDistance(userLat, userLon, building.getLatitude(), building.getLongitude());

            Log.d("DistanceDebug", building.getName()+ "  " + distance);}

        return closestBuildings;
    }

    public Building[] AddAllBuildings(){
        Building[] closestBuildings = new Building[99];

        // The array closestBuildings is an array of all the buildings on campus, the array name is misleading

        closestBuildings[0] = new Building("Ansari Business", "AB", 39.54007058321954, -119.81470412193153);
        closestBuildings[1] = new Building("Schulich Lecture Hall", "SLH", 39.5408769376099, -119.8145605669878);
        closestBuildings[2] = new Building("Leifson Physics", "LP", 39.5411347920165, -119.81405833921025);
        closestBuildings[3] = new Building("Chemistry Building", "CB", 39.540771252129545, -119.8143333037202);
        closestBuildings[4] = new Building("Scrugham Engineering and Mines", "SEM", 39.53979774918553, -119.8134053101937);
        closestBuildings[5] = new Building("University Health Building", "M745", 39.49184409511278, -119.8043307283273);
        closestBuildings[6] = new Building("Agricultural Education Health Building", "AE", 39.53793396320432, -119.8071419276513);
        closestBuildings[7] = new Building("Anderson Health Science", "AHS", 39.54943383578293, -119.8169180033855);
        closestBuildings[8] = new Building("Applied Research Facility", "ARF", 39.54468807462003, -119.8147905898937);
        closestBuildings[9] = new Building("Artemisia Building", "ARTM", 39.53872881130598, -119.8180612475661);
        closestBuildings[10] = new Building("BCN Purchasing", "PD", 39.5491054454215, -119.8182885880413);
        closestBuildings[11] = new Building("Building 058", "FRX", 39.53989658633081, -119.814500461058);
        closestBuildings[12] = new Building("Canada Hall", "CH", 39.53980491701399, -119.8178158457138);
        closestBuildings[13] = new Building("Central Services", "CS", 39.54901031756331, -119.8193480524545);
        closestBuildings[14] = new Building("Center for Molecular Medicine", "CMM", 39.55011941701203, -119.8151091358947);
        closestBuildings[15] = new Building("Child Care Facility", "CCF", 39.55054277214875, -119.8214527652187);
        closestBuildings[16] = new Building("Church Fine Arts", "CFA", 39.54138736152589, -119.8168905322218);
        closestBuildings[17] = new Building("Clark Administration", "CA", 39.53782631724442, -119.8148958168779);
        closestBuildings[18] = new Building("Claude Howard System Administration (NSHE)", "SAB", 39.55131774080492, -119.8122490880413);
        closestBuildings[19] = new Building("Computer Center", "CC", 39.54716141131195, -119.8193360457136);
        closestBuildings[20] = new Building("David Hall", "LTD", 39.24510897462628, -119.9385002844102);
        closestBuildings[21] = new Building("Davidson Math and Science Center", "DMSC", 39.53915804016227, -119.812382003386);
        closestBuildings[22] = new Building("Department of Pediatrics", "PDO", 39.54791329379318, -119.8163316015334);
        closestBuildings[23] = new Building("Dining Conference Center", "DCC", 39.53894473275393, -119.818129061058);
        closestBuildings[24] = new Building("Earthquake Engineering Laboratory", "EEL", 39.54064731068635, -119.8125706475661);
        closestBuildings[25] = new Building("Environmental Research Facility", "ERF", 39.54803431230189, -119.8194128303693);
        closestBuildings[26] = new Building("Facilities Services", "FS", 39.5417404976077, -119.8131538168777);
        closestBuildings[27] = new Building("Edna S. Brigham Clinical Education Building", "EBB", 39.54850857379747, -119.8166061610576);
        closestBuildings[28] = new Building("Fitzgerald Student Services Building", "FSSB", 39.54224775973069, -119.8160616745498);
        closestBuildings[29] = new Building("Fleischmann Agriculture", "FA", 39.53772042828378, -119.8120813444846);
        closestBuildings[30] = new Building("Fleischmann Planetarium", "FP", 39.54600129823881, -119.8191663745496);
        closestBuildings[31] = new Building("Frandsen Humanities", "FH", 39.53845424544374, -119.8151685168779);
        closestBuildings[32] = new Building("Gateway Parking Complex", "GPC", 39.53845424544374, -119.8151685168779);
        closestBuildings[33] = new Building("Great Basin Hall", "GBH", 39.5397255652871, -119.8168579286642);
        closestBuildings[34] = new Building("Harry Reid Engineering Laboratory", "HREL", 39.5405765352446, -119.8133178211709);
        closestBuildings[35] = new Building("Holman Arts & Media Center", "LTH", 39.24635978565449, -119.9356075961233);
        closestBuildings[36] = new Building("Howard Medical Sciences", "HMS", 39.54930614500257, -119.8154629457134);
        closestBuildings[37] = new Building("Innevation Center", "IC", 39.52216402514009, -119.8080915470262);
        closestBuildings[38] = new Building("Joe Crowley Student Union", "JCSU", 39.54471480122992, -119.8160496168775);
        closestBuildings[39] = new Building("Jones Center", "JC", 39.53830323455984, -119.8146320610581);
        closestBuildings[40] = new Building("Jot Travis Building", "JTB", 39.53863054749339, -119.8165553038072);
        closestBuildings[41] = new Building("Juniper Hall", "JH", 39.53800708874398, -119.8165995358584);
        closestBuildings[42] = new Building("Knudtsen Resource Center", "KRC", 39.53866406410878, -119.8070204230624);
        closestBuildings[43] = new Building("Lawlor Events Center", "LEC", 39.54508262817868, -119.8183199935299);
        closestBuildings[44] = new Building("Leifson Physics", "LP", 39.5411347920165, -119.8140583392102);
        closestBuildings[45] = new Building("Life Science", "LS", 39.53830962250762, -119.8124312279158);
        closestBuildings[46] = new Building("Lincoln Hall", "LH", 39.53939404616184, -119.8163483143171);
        closestBuildings[47] = new Building("Lombardi Recreation Center", "LRC", 39.54583244685586, -119.8150896754774);
        closestBuildings[48] = new Building("Mackay Mines", "MM", 39.53925784281859, -119.814416144483);
        closestBuildings[49] = new Building("Mackay Science", "MS", 39.53817235446425, -119.8137100739303);
        closestBuildings[50] = new Building("Mackay Stadium", "S", 39.54691546202966, -119.8175356033855);
        closestBuildings[51] = new Building("Manville Health Science", "MHS", 39.54967603750378, -119.8162253033783);
        closestBuildings[52] = new Building("Manzanita Hall", "MAH", 39.53730313732111, -119.8162293444834);
        closestBuildings[53] = new Building("Mathewson-IGT Knowledge Center", "MIKC", 39.54344534908103, -119.8157398033857);
        closestBuildings[54] = new Building("Nevada Living Learning Community", "NLLC", 39.54034528703201, -119.8177422745498);
        closestBuildings[55] = new Building("National Judicial College Building", "NJC", 39.54293239807674, -119.8139052211706);
        closestBuildings[56] = new Building("Nell J. Redfield (Student Health Center)", "NJR", 39.54824683515152, -119.8163280033792);
        closestBuildings[57] = new Building("Nell J. Redfield Building A, Redfield Campus", "RC-A", 39.39166142141875, -119.7651237092755);
        closestBuildings[58] = new Building("Nellor Biomedical Sciences", "NBS", 39.54974493258539, -119.8166275522579);
        closestBuildings[59] = new Building("Nevada State Health Laboratory", "NSHL", 39.54894008917429, -119.818006734262);
        closestBuildings[60] = new Building("Nye Hall", "NH", 39.5391506124423, -119.8178864479671);
        closestBuildings[61] = new Building("Orvis Building", "OB", 39.53806740306788, -119.812924844484);
        closestBuildings[62] = new Building("Palmer Engineering", "PE", 39.53944404825683, -119.8130375266921);
        closestBuildings[63] = new Building("Parking Services", "PS", 39.54661840332351, -119.8199783622738);
        closestBuildings[64] = new Building("Patterson Hall", "LTPA", 39.24430949386029, -119.9393596985843);
        closestBuildings[65] = new Building("Paul Laxalt Mineral Engineering", "LME", 39.53907873385332, -119.8136979264037);
        closestBuildings[66] = new Building("Paul Laxalt Mineral Research", "LMR", 39.53870297159459, -119.8132350033817);
        closestBuildings[67] = new Building("Peavine Hall", "PH", 39.53803450073156, -119.8179938426648);
        closestBuildings[68] = new Building("William N. Pennington Engineering Building", "WPEB", 39.53986785587337, -119.811881868504);
        closestBuildings[69] = new Building("William N. Pennington Health Sciences Building", "PHS", 39.54915433520576, -119.8137040089017);
        closestBuildings[70] = new Building("William N. Pennington Medical Education", "PMB", 39.54859900801333, -119.8158780033775);
        closestBuildings[71] = new Building("William N. Pennington Student Achievement Center", "PSAC", 39.53951022217315, -119.8152476622744);
        closestBuildings[72] = new Building("Marguerite W. Petersen Athletic Academic Center", "MWPB", 39.54558271262498, -119.8168633330817);
        closestBuildings[73] = new Building("Ponderosa Village", "PV", 39.54623570850347, -119.8141445036932);
        closestBuildings[74] = new Building("Prim Library", "LTPL", 39.24303719876431, -119.9391250034003);
        closestBuildings[75] = new Building("Prim-Schultz Hall", "LTPS", 39.24481460416749, -119.938885329511);
        closestBuildings[76] = new Building("Real Estate Office", "REO", 39.5362181388223, -119.8150904201095);
        closestBuildings[77] = new Building("Renewable Resource Center", "RRC", 39.53831173456035, -119.80732907455);
        closestBuildings[78] = new Building("PBS Reno", "PBS", 39.54806183513602, -119.8205142033818);
        closestBuildings[79] = new Building("Reynolds School of Journalism", "RSK", 39.5414638091013, -119.8152355966358);
        closestBuildings[80] = new Building("Robert Cashell Fieldhouse", "CFH", 39.54580548379871, -119.8163889800642);
        closestBuildings[81] = new Building("Ross Hall", "RH", 39.53860578063892, -119.8146502411706);
        closestBuildings[82] = new Building("Sarah H. Fleischmann Building", "SFB", 39.53770821100202, -119.8128889448786);
        closestBuildings[83] = new Building("Savitt Medical Science", "SMS", 39.54886971260652, -119.816389644482);
        closestBuildings[84] = new Building("Schulich Lecture Hall", "SLH", 39.5408769376099, -119.8145605669878);
        closestBuildings[85] = new Building("Sierra Hall", "SH", 39.53731653449576, -119.817040703381);
        closestBuildings[86] = new Building("Sierra Street Parking Complex", "SPC", 39.54014586524239, -119.8188397268143);
        closestBuildings[87] = new Building("Sports Medicine Complex", "SMC", 39.54622024472516, -119.8160090966358);
        closestBuildings[88] = new Building("Tahoe Center for Environmental Sciences", "LTC", 39.24262905387518, -119.9396452745643);
        closestBuildings[89] = new Building("Thompson Building", "TB", 39.539022671903, -119.8152595362059);
        closestBuildings[90] = new Building("University Foundation Arts", "UFA", 39.54127371662348, -119.8156977594845);
        closestBuildings[91] = new Building("Utility Plant", "UP", 39.54013207159345, -119.8139716033815);
        closestBuildings[92] = new Building("Valley Road Greenhouse Complex", "VRGC", 39.53776836351721, -119.8061585616256);
        closestBuildings[93] = new Building("Virginia Street Gym", "VSG", 39.5402145085141, -119.816587385587);
        closestBuildings[94] = new Building("Visitors Locker Room", "SL", 39.5402145085141, -119.816587385587);
        closestBuildings[95] = new Building("West Stadium Parking Complex", "WSPC", 39.54676030332822, -119.8189906444831);
        closestBuildings[96] = new Building("Brian J. Whalen Parking Complex", "BWPC", 39.54301146245057, -119.8171113417476);
        closestBuildings[97] = new Building("William J. Raggio", "WRB", 39.54203468203159, -119.8150934033857);
        closestBuildings[98] = new Building("William Peccole Park", "WPP", 39.54842115229084, -119.8127666523233);


        return closestBuildings;

    }
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        // This function implemented from https://stackoverflow.com/questions/33825626/calculate-the-distance-between-two-geographical-coordinates-in-java

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 1.609344 * 1000;
        return (dist);
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    /* The function to convert radians into decimal */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}

