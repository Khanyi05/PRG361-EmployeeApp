/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeeapp.controller;
import employeeapp.model.Payroll;
import employeeapp.model.PayrollD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Khanyisile
 */
public class PayrollController {
     private PayrollD payrollD;

     //constructor
    public PayrollController(Connection connection) {
        this.payrollD = new PayrollD(connection);
    }

    // Fetch all payroll records
    public List<Payroll> getAllPayrolls() throws SQLException {
        return payrollD.getAllPayrolls();
    }

    // Fetch payroll records for a specific employee
    public List<Payroll> getPayrollsByEmployeeID(int employeeID) throws SQLException {
        return payrollD.getPayrollsByEmployeeID(employeeID);
    }

    // Add a new payroll record
    public void addPayroll(Payroll payroll) throws SQLException {
        payrollD.addPayroll(payroll);
    }

    // Update a payroll record
    public void updatePayroll(Payroll payroll) throws SQLException {
        payrollD.updatePayroll(payroll);
    }

    // Delete a payroll record
    public void deletePayroll(int payrollID) throws SQLException {
        payrollD.deletePayroll(payrollID);
    }
}
