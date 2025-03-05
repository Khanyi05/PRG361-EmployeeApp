/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeeapp.controller;
import employeeapp.model.Employee;
import employeeapp.model.EmployeeD;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
/**
 *
 * @author Khanyisile
 */
public class EmployeeController {
    private EmployeeD employeeD;

    //constructor
    public EmployeeController(Connection connection) {
        this.employeeD = new EmployeeD(connection);
    }

    // Fetch all employees
    public List<Employee> getAllEmployees() throws SQLException {
        try {
            return employeeD.getAllEmployees();
        } catch (SQLException e) {
            System.err.println("Error fetching employees: " + e.getMessage());
            return null;
        }
    }

    // Fetch an employee by ID
    public Employee getEmployeeByID(int employeeID) throws SQLException {
        try {
            return (Employee) employeeD.getEmployeeByID(employeeID);
        } catch (SQLException e) {
            System.err.println("Error fetching employee: " + e.getMessage());
            return null;
        }
    }

    // Add a new employee
    public void addEmployee(Employee employee) throws SQLException, ParseException {
        employeeD.addEmployee(employee);
    }

    // Update an employee
    public void updateEmployee(Employee employee) throws SQLException {
        employeeD.updateEmployee(employee);
    }

    // Delete an employee
    public void deleteEmployee(int employeeID) throws SQLException {
        employeeD.deleteEmployee(employeeID);
    }
}
        