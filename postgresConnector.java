/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;
import java.sql.*;

/**
 *
 * @author natha
 */
import java.util.ArrayList;
import java.util.HashMap;
public class postgresConnector {
  
    private static final String url = "jdbc:postgresql://localhost:5432/Employee Registry_DB";
    private static final String username = "Nathan";
    private static final String password = "Nathanrox";

    public Connection initConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    
    //The insert function below describes a method that takes employee data as arguments and turns it into a query. then adds it to a database.     
    public boolean insertData(String id, String name, String surname, String role, String department, String email, String phone, String password) {
        String query = "INSERT INTO employee (id, name, surname, role, department, email, phone, password) VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = initConnection();
            statement = conn.prepareStatement(query);

            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, role);
            statement.setString(5, department);
            statement.setString(6, email);
            statement.setString(7, phone);
            statement.setString(8, password); // Remember to hash the password in a real application

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> fetchDataIds() {
        String query = "SELECT id FROM employee";
        Connection conn = null;
        PreparedStatement preparedStatement = null;  //allows for statically compiled sql statements, also uses ? for parameters to be inputted later
        ResultSet cursor = null;
        ArrayList<String> idList = new ArrayList<>();

        try {
            conn = initConnection();
            preparedStatement = conn.prepareStatement(query);
            cursor = preparedStatement.executeQuery();

            while (cursor.next()) {
                String idValue = cursor.getString("id"); // Create variable first
                idList.add(idValue); // Then add to the list
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cursor != null) {
                    cursor.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idList;
        
        
    }
        
        //The code below maps the emp id and password to data retrieved from a database to validate a login for the employee
        
      public Boolean employeeLogin(String empID, String password){
          Connection conn = null;
          PreparedStatement statement = null;
          ResultSet cursor = null;
          HashMap <String, String> logincredentials = new HashMap<>();
          
          try{
              conn = initConnection(); //initialise connection with username, password and url to database
              while (cursor.next()) {
                String employeeId = cursor.getString("id");
                String storedPassword = cursor.getString("password");
                logincredentials.put(employeeId, storedPassword); //Hashmap put method to add to the list
            }

            // Check if the username (employeeId) and password match using .contains method in HashMap
            if (logincredentials.containsKey(empID) && logincredentials.get(empID).equals(password)) {
                return true; // Login successful
                
            } else {
                return false; // Login failed
            }
          }
          
          
          catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (cursor!= null) {
                    cursor.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
              
      }  
      
    }
    
    
    
    
    
    
} 
    
  
    

