/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeeapp.controller;
import employeeapp.model.Role;
import employeeapp.model.RoleD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Khanyisile
 */
public class RoleController {
     private RoleD roleD;

    //constructor
    public RoleController(Connection connection) {
        this.roleD = new RoleD(connection);
    }

    // Fetch all roles
    public List<Role> getAllRoles() throws SQLException {
        return roleD.getAllRoles();
    }

    // Fetch a role by ID
    public Role getRoleByID(int roleID) throws SQLException {
        return roleD.getRoleByID(roleID);
    }

    // Add a new role
    public void addRole(Role role) throws SQLException {
        roleD.addRole(role);
    }

    // Update a role
    public void updateRole(Role role) throws SQLException {
        roleD.updateRole(role);
    }

    // Delete a role
    public void deleteRole(int roleID) throws SQLException {
        roleD.deleteRole(roleID);
    }
}
