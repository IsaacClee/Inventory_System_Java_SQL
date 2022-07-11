package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Primary connection to Database
 * Used by all other DB (Database) functions
 *
 */
public abstract class JDBC {

    /**
     * Used to construct connection string
     */
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_Schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; //LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static final String password = "Passw0rd!"; // Password
    public static Connection connection; // Connection Interface


    /**
     * Used to initialize a connection to the database
     * @return connection
     */
    public static Connection openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        } catch(SQLException e)
        {
            e.printStackTrace();
        } catch(ClassNotFoundException e)
        {
           e.printStackTrace();
        }
        return connection;
    }

    /**
     * Getter for the Database Connection
     * @return connection
     */
    public static Connection getConnection(){
        return connection;
    }

    /**
     * Used to close the connection to the Database
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error" + e.getMessage());
        }
    }

}
