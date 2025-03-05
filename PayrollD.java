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
import java.util.Date;
import java.util.List;
/**
 *
 * @author Khanyisile
 */
public class PayrollD {
     private final Connection connection;

    // Constructor
    public PayrollD(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new payroll record
    public void addPayroll(Payroll payroll) throws SQLException {
        String query = "INSERT INTO Payrolls (PayrollID, EmployeeID, Salary, PayDate, Description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, payroll.getPayrollID());
            pstmt.setInt(2, payroll.getEmployeeID());
            pstmt.setDouble(3, payroll.getSalary());
            pstmt.setDate(4, payroll.getPayDate());
            pstmt.setString(5, payroll.getDescrip());
            pstmt.executeUpdate();
        }
    }

    // Method to fetch all payroll records
    public List<Payroll> getAllPayrolls() throws SQLException {
        List<Payroll> payrolls = new ArrayList<>();
        String query = "SELECT * FROM Payrolls";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Payroll payroll = new Payroll(
                    rs.getInt("PayrollID"),
                    rs.getInt("EmployeeID"),
                    rs.getDouble("Salary"),
                    rs.getDate("PayDate"),
                    rs.getString("Description")
                );
                payrolls.add(payroll);
            }
        }
        return payrolls;
    }

    // Method to fetch payroll records for a specific employee
    public List<Payroll> getPayrollsByEmployeeID(int employeeID) throws SQLException {
        List<Payroll> payrolls = new ArrayList<>();
        String query = "SELECT * FROM Payrolls WHERE EmployeeId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    Payroll payroll = new Payroll(
                            rs.getInt("PayrollID"),
                            rs.getInt("EmployeeID"),
                            rs.getDouble("Salary"),
                            rs.getDate("PayDate"),
                            rs.getString("Description")
                    );
                    payrolls.add(payroll);
                }
            }
        }
        return payrolls;
    }

    // Method to update a payroll record
    public void updatePayroll(Payroll payroll) throws SQLException {
        String query = "UPDATE Payrolls SET EmployeeID = ?, Salary = ?, PayDate = ? WHERE PayrollID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, payroll.getEmployeeID());
            pstmt.setDouble(2, payroll.getSalary());
            pstmt.setDate(3, payroll.getPayDate());
            pstmt.setInt(4, payroll.getPayrollID());
            pstmt.executeUpdate();
        }
    }

    // Method to delete a payroll record
    public void deletePayroll(int payrollID) throws SQLException {
        String query = "DELETE FROM Payrolls WHERE PayrollID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, payrollID);
            pstmt.executeUpdate();
        }
    }
    
    //Method to return payslip
    public String PaySlip(int employeeID) throws SQLException {
        String queryPayroll = "SELECT PayrollID, Salary, PayDate, Description FROM Payrolls WHERE EmployeeID = ?";
        String queryEmployee = "SELECT Name, Surname, RoleID, DepartmentID FROM Employees WHERE EmployeeID = ?";

        StringBuilder payslipDetails = new StringBuilder(); 
        
        String border = "+--------------------------------------------------------+\n";
        payslipDetails.append(border);
        payslipDetails.append(String.format("|                    PAYSLIP                             |\n"));
        payslipDetails.append(String.format("|                Employee Payslip                        |\n"));
        payslipDetails.append(border);

        double totalSalary = 0;
        
        try (PreparedStatement pstmtPayroll = connection.prepareStatement(queryPayroll);
             PreparedStatement pstmtEmployee = connection.prepareStatement(queryEmployee)) {

            pstmtPayroll.setInt(1, employeeID);
            pstmtEmployee.setInt(1, employeeID);

            try (ResultSet rsPayroll = pstmtPayroll.executeQuery();
                 ResultSet rsEmployee = pstmtEmployee.executeQuery()) {
                
                if (rsPayroll.next() && rsEmployee.next()) {
                    int payrollID = rsPayroll.getInt("PayrollID");

                    String name = rsEmployee.getString("Name") + " " + rsEmployee.getString("Surname");
                    String department = rsEmployee.getString("DepartmentID");  
                    String role = rsEmployee.getString("RoleID");  
                    
                    payslipDetails.append(String.format("| Payroll ID: %-38d|\n", payrollID));
                    payslipDetails.append(String.format("| Employee Name: %-33s|\n", name));
                    payslipDetails.append(String.format("| Role: %-37s|\n", role));
                    payslipDetails.append(String.format("| Department: %-37s|\n", department));

                    while (rsPayroll.next()) {
                        double salary = rsPayroll.getDouble("Salary");
                        Date payDate = rsPayroll.getDate("PayDate");
                        String description = rsPayroll.getString("Description");

                        payslipDetails.append(String.format("| Pay Date: %-41s|\n", payDate));
                        payslipDetails.append(String.format("| Salary: %-44.2f|\n", salary));
                        payslipDetails.append(String.format("| Description: %-38s|\n", description));

                        totalSalary += salary;
                    }
                    payslipDetails.append(String.format("| Total Salary: %-39.2f|\n", totalSalary));

                } else {
                    payslipDetails.append("| No payroll or employee records found for the given Employee ID. |\n");
                }
            } catch (SQLException e) {
                payslipDetails.append("| Error fetching data from the database. Please try again later. |\n");
            }
        }
        payslipDetails.append(border);
        payslipDetails.append("|             Thank you for your hard work!                |\n");
        payslipDetails.append(border);

    return payslipDetails.toString();
}

}
