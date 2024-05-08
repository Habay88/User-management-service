package com.users.service;

import java.util.List;

import com.users.entity.Roles;
import com.users.entity.Users;

public interface IRoleService {
    
    List<Roles> getAllRoles();
    Roles createRole(Roles theRole);
    void deleteRole(Long roleId);
    Roles findByName(String name);
    Roles findById(Long roleId);
    Users removeUserFromRole(Long userId , Long roleId);
    Users assignUserToRole(Long userId, long roleId);
    Roles removeAllUserFromRole(Long roleId);
}