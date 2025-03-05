package employeeapp.model;

/**
 *
 * @author Khanyisile
 */
public class Role {
    private int roleID;
    private String roleName;

    // Constructor
    public Role(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    // Getters and Setters
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
