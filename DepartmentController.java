/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeeapp.controller;
import employeeapp.model.Department;
import employeeapp.model.DepartmentD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Khanyisile
 */
public class DepartmentController {
    private final DepartmentD departmentD;

    //Construcctor
    public DepartmentController(Connection connection) {
        this.departmentD = new DepartmentD(connection);
    }

    // Fetch all departments
    public List<Department> getAllDepartments() throws SQLException {
        return departmentD.getAllDepartments();
    }

    // Add a new department
    public void addDepartment(Department department) throws SQLException {
        departmentD.addDepartment(department);
    }

    // Update a department
    public void updateDepartment(Department department) throws SQLException {
        departmentD.updateDepartment(department);
    }

    // Delete a department
    public void deleteDepartment(int departmentID) throws SQLException {
        departmentD.deleteDepartment(departmentID);
    }
}
