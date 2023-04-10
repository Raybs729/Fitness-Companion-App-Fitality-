package com.techelevator.model;

public class AccountRole {
    private int roleId;
    private String roleName;

    public AccountRole() { }

    public AccountRole(int roleId, String roleName) {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
