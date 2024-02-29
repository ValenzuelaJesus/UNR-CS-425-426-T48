import java.sql.*;
public class main {
    public static void main(String[] args) {
        // Connect to the PostgreSQL database
        Connection connection = building.connect();
        Statement query = null;
        ResultSet query_result = null;

        // Perform database operations here
        try{
            query = connection.createStatement();
            String selectAllquery = "SELECT building_name, building_code, ST_X(gps_coords::geometry) AS longitude, ST_Y(gps_coords::geometry) AS latitude FROM building";
            query_result = query.executeQuery(selectAllquery);

            while(query_result.next()){
                String input_buildingCode = query_result.getString("building_code");
                String input_buildingName = query_result.getString("building_name");
                double input_latitude = query_result.getDouble("latitude");
                double input_longitude = query_result.getDouble("longitude");
                System.out.println(input_buildingCode);
                System.out.println(input_buildingName);
                System.out.println(input_latitude);
                System.out.println(input_longitude);
            }
        } catch (SQLException e){
            System.out.println("Error Query Failed");
            e.printStackTrace();
        } finally {
            try{
                if (query_result != null) query_result.close();
                if (query != null) query.close();
                if (connection != null) connection.close();
            } catch(SQLException e){ e.printStackTrace();}
        }

        // Close the database connection
        building.disconnect(connection);
    }
}