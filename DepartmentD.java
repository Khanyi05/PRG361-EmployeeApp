/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeeapp.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khanyisile
 */
public class DepartmentD {
    private final Connection connection;
    
    //Constructo
    public DepartmentD(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new department
    public void addDepartment(Department department) throws SQLException {
        String query = "INSERT INTO Departments (DepartmentID, Department_Name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, department.getDepartmentID());
            pstmt.setString(2, department.getDepartmentName());
            pstmt.executeUpdate();
        }
    }

    // Method to fetch all departments
    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM Departments";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Department department = new Department(
                    rs.getString("DepartmentID"),
                    rs.getString("Department_Name")
                );
                departments.add(department);
            }
        }
        return departments;
    }

    // Method to update an existing department
    public void updateDepartment(Department department) throws SQLException {
        String query = "UPDATE Departments SET Department_Name = ? WHERE DepartmentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, department.getDepartmentName());
            pstmt.setString(2, department.getDepartmentID());
            pstmt.executeUpdate();
        }
    }

    // Method to delete a department
    public void deleteDepartment(int departmentID) throws SQLException {
        String query = "DELETE FROM Departments WHERE DepartmentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, departmentID);
            pstmt.executeUpdate();
        }
    }
}
