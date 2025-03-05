/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeeapp.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
 
public class EmployeeD {
     private final Connection connection;

    // Constructor
    public EmployeeD(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new employee
    public void addEmployee(Employee employee) throws SQLException, ParseException {
        String checkQuery = "SELECT COUNT(*) FROM Employees WHERE EMPLOYEEID = ?";
        String query = "INSERT INTO Employees (EMPLOYEEID, Name, Surname, Gender, DateOfBirth, Email, PhoneNumber, Address, RoleID, DepartmentID, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)  ";
        String payquery = "INSERT INTO PAYROLLS (PAYROLLID, EMPLOYEEID, SALARY, PAYDATE, DESCRIPTION) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement checkPstmt = connection.prepareStatement(checkQuery);
                PreparedStatement payPsmt = connection.prepareStatement(payquery);
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            // Generate new emp id
            Random random = new Random();
            int randomInt = 0;
            boolean isUnique = false;   
            
            while (!isUnique) {
                randomInt = random.nextInt(10000, 100000); // Generate random number between 10000 and 100000

                // Check if the generated emp id in emplo
                checkPstmt.setInt(1, randomInt);
                try (ResultSet rs = checkPstmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        isUnique = true; //emp id does not exist, so it's unique
                    }
                }
            }
            
            //pay date builder
            LocalDate currentDate = LocalDate.now();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateWith25thDay = currentDate.withDayOfMonth(25).toString();
            Date payDate = sdf.parse(dateWith25thDay);
            java.sql.Date sqlDate = new java.sql.Date(payDate.getTime());
            
            //Description builder
            Month currentMonth = currentDate.getMonth();
            String sDescrip = "Monthly Salary for " + currentMonth.toString();

            //employee tbl entries
            pstmt.setInt(1, randomInt);
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getSurname());
            pstmt.setString(4, String.valueOf(employee.getGender()));
            pstmt.setDate(5, employee.getDateOfBirth());
            pstmt.setString(6, employee.getEmail());
            pstmt.setString(7, employee.getPhoneNumber());
            pstmt.setString(8, employee.getAddress());
            pstmt.setString(9, employee.getRoleID());
            pstmt.setString(10, employee.getDepartmentID());
            pstmt.setDouble(11, employee.getSalary());
            pstmt.executeUpdate();
            
            //payroll tbl entries
            payPsmt.setInt(1, randomInt);
            payPsmt.setInt(2, randomInt);
            payPsmt.setDouble(3, employee.getSalary());
            payPsmt.setDate(4, sqlDate);
            payPsmt.setString(5, sDescrip);
            payPsmt.executeUpdate();
        }
    }

    // Method to fetch all employees
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM EMPLOYEES";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getInt("EmployeeID"),
                    rs.getString("Name"),
                    rs.getString("Surname"),
                    rs.getString("Gender").charAt(0),
                    rs.getDate("DateOfBirth"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Address"),
                    rs.getString("RoleID"),
                    rs.getString("DepartmentID"),
                    rs.getDouble("Salary")
                );
                employees.add(employee);
            }
        }
        return employees;
    }

    // Method to fetch an employee by ID
    public Employee getEmployeeByID(int employeeID) throws SQLException {
        String query = "SELECT * FROM EMPLOYEES WHERE EMPLOYEEID = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("Gender").charAt(0),
                        rs.getDate("DateOfBirth"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("RoleID"),
                        rs.getString("DepartmentID"),
                        rs.getDouble("Salary")
                    );
                }
            }
        }
        return null; // Employee not found
    }

    // Method to update an employee
    public void updateEmployee(Employee employee) throws SQLException {
        String query = "UPDATE Employees SET Name = ?, Surname = ?, Gender = ?, DateOfBirth = ?, Email = ?, PhoneNumber = ?, Address = ?, RoleID = ?, DepartmentID = ?, Salary = ? WHERE EmployeeID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getSurname());
            pstmt.setString(3, String.valueOf(employee.getGender()));
            pstmt.setDate(4, employee.getDateOfBirth());
            pstmt.setString(5, employee.getEmail());
            pstmt.setString(6, employee.getPhoneNumber());
            pstmt.setString(7, employee.getAddress());
            pstmt.setString(8, employee.getRoleID());
            pstmt.setString(9, employee.getDepartmentID());
            pstmt.setDouble(10, employee.getSalary());
            pstmt.setInt(11, employee.getEmployeeID());
            pstmt.executeUpdate();
        }
    }

    // Method to delete an employee
    public void deleteEmployee(int employeeID) throws SQLException {
        String query = "DELETE FROM EMPLOYEES WHERE EMPLOYEEID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            pstmt.executeUpdate();
        }
    }
}
