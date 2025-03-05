/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeeapp.model;
import java.sql.Date;
/**
 *
 * @author Khanyisile
 */
public class Payroll {
    private int payrollID;
    private int employeeID;
    private double salary;
    private Date payDate;
    private String sDescrip;

    // Constructor
    public Payroll(int payrollID, int employeeID, double salary, Date payDate, String Description) {
        this.payrollID = payrollID;
        this.employeeID = employeeID;
        this.salary = salary;
        this.payDate = payDate;
        this.sDescrip = Description;
    }

    // Getters and Setters
    public int getPayrollID() {
        return payrollID;
    }

    public void setPayrollID(int payrollID) {
        this.payrollID = payrollID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
    
    public String getDescrip() {
        return sDescrip;
    }

    public void setDescrip(String sDes) {
        this.sDescrip = sDes;
    }
    
     @Override
    public String toString() {
        return "Payrolls{" +
                "payrollID=" + payrollID +
                ", employeeID=" + employeeID +
                ", salary=" + salary +
                ", payDate=" + payDate +
                '}';
    }
}
