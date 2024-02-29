import java.sql.*;

//import javax.naming.spi.DirStateFactory.Result;


//import javax.print.DocFlavor.STRING;

public class building{
    //Database Paramaters
    //Using local database for development. UPDATE when using in frontend with relevant google cloud ip address and user account
    //private static final String database_details = "jdbc:postgresql://localhost:5432/cs_426_database";
    //private static final String db_username = "postgres";
    //private static final String db_password = "842697135"; //Hide important details when posting to GITHUB
    
    private String gps_coords;
    private double latitude;
    private double longitude;
    private String building_code;
    private int building_num;
    private String department;
    private String operating_hours;

    // //Database connection management
    // public static Connection connect(){
    //     Connection connection = null;
    //     try { //Load the PostgreSQL 
    //         Class.forName("org.postgresql.Driver");
    //         connection = DriverManager.getConnection(database_details, db_username, db_password);
    //         System.out.println("Connected to Database");

    //     } catch (ClassNotFoundException | SQLException e) {
    //         System.out.println("Database Conneciton failed!");
    //         e.printStackTrace();
    //     } 
    //     return connection;
    // }

    // public static void disconnect(Connection connection){
    //     if(connection != null){
    //         try {
    //             connection.close();
    //             System.out.println("Connection Closed");
    //         } catch(SQLException e) {
    //             System.out.println("Error disconnect attempt faile failed");
    //             e.printStackTrace();
    //         }
    //     }
    // }


    //Constructor
    public building(String input_gpscoords, double input_latitude, double input_longitude, String input_building_code, int input_building_num, String input_department, String input_operating_hours){
        gps_coords = input_gpscoords;
        latitude= input_latitude;
        longitude = input_longitude;
        building_code = input_building_code;
        building_num = input_building_num;
        department = input_department;
        operating_hours = input_operating_hours;
    }

    //Getters
    public String getGpsCoords(){
        return gps_coords;
    } 
    
    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public String getBuildingCode(){
        return building_code;
    }

    public int getBuildingNum(){
        return building_num;
    }

    public String getDepartment(){
        return department;
    }

    public String getOperatingHours(){
        return operating_hours;
    }

    //Setters
    public void setGpsCoords(String gpsCoords) {
        this.gps_coords = gpsCoords;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setBuildingCode(String buildingCode) {
        this.building_code = buildingCode;
    }

    public void setBuildingNum(int buildingNum) {
        this.building_num = buildingNum;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setOperatingHours(String operatingHours) {
        this.operating_hours = operatingHours;
    }

    //Code for testing and example integration
    public static Connection connect(){
        Connection connection = null;
        try { //Load the PostgreSQL 
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(database_details, db_username, db_password);
            System.out.println("Connected to Database");
    
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database Conneciton failed!");
            e.printStackTrace();
        } 
        return connection;
    }
    
    public static void disconnect(Connection connection){
        if(connection != null){
            try {
                connection.close();
                System.out.println("Connection Closed");
            } catch(SQLException e) {
                System.out.println("Error disconnect attempt faile failed");
                e.printStackTrace();
            }
        }
    }
    
//     public static void main(String[] args) {
//         // Connect to the PostgreSQL database
//         Connection connection = connect();
//         Statement query = null;
//         ResultSet query_result = null;

//         // Perform database operations here
//         try{
//             query = connection.createStatement();
//             String selectAllquery = "SELECT building_name, building_code, ST_X(gps_coords::geometry) AS longitude, ST_Y(gps_coords::geometry) AS latitude FROM building";
//             query_result = query.executeQuery(selectAllquery);

//             while(query_result.next()){
//                 String input_buildingCode = query_result.getString("building_code");
//                 String input_buildingName = query_result.getString("building_name");
//                 double input_latitude = query_result.getDouble("latitude");
//                 double input_longitude = query_result.getDouble("longitude");
//                 System.out.println(input_buildingCode);
//                 System.out.println(input_buildingName);
//                 System.out.println(input_latitude);
//                 System.out.println(input_longitude);
//             }
//         } catch (SQLException e){
//             System.out.println("Error Query Failed");
//             e.printStackTrace();
//         } finally {
//             try{
//                 query_result.close();
//                 query.close();
//                 connection.close();
//             } catch(SQLException e){ e.printStackTrace();}
//         }

//         // Close the database connection
//         disconnect(connection);
//     }
// }
    

