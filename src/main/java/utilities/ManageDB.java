package utilities;

import java.sql.DriverManager;


// Method Name : openConnection
// Method Description: Opens a connection to a MySQL database.
// Method Parameters :
//   - string dbURL: The URL of the database.
//   - string user : The username for database authentication.
//   - string pass : The password for database authentication.
public class ManageDB extends CommonOps{
    public static void openConnection(String dbURL, String user, String pass){
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(dbURL,user,pass);  // Establish a connection to the database
            stmt = con.createStatement();  // Create a statement for executing SQL queries
            System.out.println("Connected to DB");
        } catch (Exception e) {  // Handle exceptions (e.g., if there's an error in connecting to the database)
            System.out.println("Error occured while connecting to DB, see details: " + e);
        }
    }

    // Method Name : closeConnection
    // Method Description: Closes the connection to the MySQL database.
    public static void closeConnection(){
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error occured while closing DB, see details: " + e);
        }
    }
}
