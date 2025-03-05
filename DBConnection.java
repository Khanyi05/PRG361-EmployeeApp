/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeeapp.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/

/**
 *
 * @author Khanyisile
 */
public class DBConnection {
    // Use ClientDriver for network server Derby
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    // Use the network server URL
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/EmployeeDB;user=admin1;password=12345";
    private Connection connection;

    public DBConnection() {
        // Constructor
    }
    
    //Intializer
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        this.connection = DriverManager.getConnection(JDBC_URL);
        if (this.connection != null) {
            System.out.println("Connected to the database");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
