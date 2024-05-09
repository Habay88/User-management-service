package com.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.users.com.users.exception.RoleAlreadyExistException;
import com.users.com.users.exception.UserAlreadyExistsException;
import com.users.com.users.exception.UserNotFoundException;

import com.users.entity.Roles;
import com.users.entity.Users;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Roles> getAllRoles() {

        return roleRepository.findAll();
    }

    @Override
    public Roles createRole(Roles theRole) {
        Optional<Roles> checkRole = roleRepository.findByName(theRole.getName());
        if (checkRole.isPresent()) {
            throw new RoleAlreadyExistException(checkRole.get().getName() + "role already exist");
        }
        return roleRepository.save(theRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUserFromRole(roleId);
        roleRepository.deleteById(roleId);
    }

    @Override
    public Roles findByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public Roles findById(Long roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Override
    public Users removeUserFromRole(Long userId, Long roleId) {
        Optional<Users> user = userRepository.findById(userId);
        Optional<Roles> role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())) {
            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }
        throw new UserNotFoundException("User not found");
    }

    @Override
    public Users assignUserToRole(Long userId, long roleId) {
        Optional<Users> user = userRepository.findById(userId);
        Optional<Roles> role = roleRepository.findById(roleId);
        if (user.isPresent() && user.get().getRoles().contains(role.get())) {
            throw new UserAlreadyExistsException(
                    user.get().getFirstName() + " is already assigned to the " + role.get().getName() + "role");
        }
        role.ifPresent(theRole -> theRole.assignUserToRole(user.get()));
        roleRepository.save(role.get());
        return user.get();
    }

    @Override
    public Roles removeAllUserFromRole(Long roleId) {
        Optional<Roles> role = roleRepository.findById(roleId);
        role.ifPresent(Roles::removeAllUsersFromRole);
        return roleRepository.save(role.get());
    }

}