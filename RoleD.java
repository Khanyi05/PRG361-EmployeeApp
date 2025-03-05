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
public class RoleD {
    private final Connection connection;

    // Constructor
    public RoleD(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new role
    public void addRole(Role role) throws SQLException {
        String query = "INSERT INTO Roles (RoleID, RoleName) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, role.getRoleID());
            pstmt.setString(2, role.getRoleName());
            pstmt.executeUpdate();
        }
    }

    // Method to fetch all roles
    public List<Role> getAllRoles() throws SQLException {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM Roles";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Role role = new Role(
                    rs.getInt("RoleID"),
                    rs.getString("RoleName")
                );
                roles.add(role);
            }
        }
        return roles;
    }

    // Method to fetch a role by ID
    public Role getRoleByID(int roleID) throws SQLException {
        String query = "SELECT * FROM Roles WHERE RoleID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roleID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Role(
                        rs.getInt("RoleID"),
                        rs.getString("RoleName")
                    );
                }
            }
        }
        return null; // Role not found
    }

    // Method to update a role
    public void updateRole(Role role) throws SQLException {
        String query = "UPDATE Roles SET RoleName = ? WHERE RoleID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, role.getRoleName());
            pstmt.setInt(2, role.getRoleID());
            pstmt.executeUpdate();
        }
    }

    // Method to delete a role
    public void deleteRole(int roleID) throws SQLException {
        String query = "DELETE FROM Roles WHERE RoleID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, roleID);
            pstmt.executeUpdate();
        }
    }
}
