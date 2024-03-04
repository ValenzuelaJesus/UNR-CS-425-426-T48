import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class unittests{

    private final ByteArrayOutputStream  system_out = new ByteArrayOutputStream();

    @Test //Testing pgJDBC connection creation. Building method connect creates the connection object that will be used.
    public void testDatabaseConnection() throws SQLException{
        Connection database_connection = building.connect();
        assertNotNull(database_connection); //The database connection if successfull will return a connection object if the connection
        //failed than no return will occur
        assertTrue(database_connection.isValid(5)); //Will return true if the connection is still 
        //open for the specifed time in seconds
        building.disconnect(database_connection);
    }
    @Before
    public void setup(){
        System.setOut(new PrintStream(system_out));
    }
    @After
    public void delete(){
        System.setOut(System.out);
    }

    @Test //Testing database connection
    public void testDatabaseDisconnection(){
        Connection database_connection = building.connect();
        building.disconnect(database_connection);
        assertEquals("Connected to Database\r\n" + //
                        "Connection Closed", system_out.toString().trim());
    }


}
