package com.users.request;

import java.util.Set;

public class AssignRoleRequest {
    private Set<String> roleNames;

    // Getters and setters
    public Set<String> getRoleNames() { return roleNames; }
    public void setRoleNames(Set<String> roleNames) { this.roleNames = roleNames; }
}
