package com.users.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.users.dto.CreateRoleRequest;
import com.users.entity.Role;
import com.users.entity.User;
import com.users.repository.RoleRepository;
import com.users.repository.UserRepository;
import com.users.request.AssignRoleRequest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/super-admin")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class SuperAdminController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@Valid @RequestBody CreateRoleRequest request) {
        if (roleRepository.existsByName(request.getName())) {
            return ResponseEntity.badRequest().body("Error: Role name already exists!");
        }

        Role role = new Role(request.getName(), request.getDescription());
        role.setPermissions(new HashSet<>(request.getPermissions()));
        roleRepository.save(role);

        return ResponseEntity.ok("Role created successfully!");
    }

    @PutMapping("/roles/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable Long roleId, @Valid @RequestBody CreateRoleRequest request) {
        Optional<Role> roleOpt = roleRepository.findById(roleId);
        if (roleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Role role = roleOpt.get();
        if (role.isSystemRole()) {
            return ResponseEntity.badRequest().body("Error: System roles cannot be modified!");
        }

        // Check if name conflict with other roles
        Optional<Role> existingRole = roleRepository.findByName(request.getName());
        if (existingRole.isPresent() && !existingRole.get().getId().equals(roleId)) {
            return ResponseEntity.badRequest().body("Error: Role name already exists!");
        }

        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setPermissions(new HashSet<>(request.getPermissions()));
        roleRepository.save(role);

        return ResponseEntity.ok("Role updated successfully!");
    }

    @PostMapping("/users/{userId}/roles")
    public ResponseEntity<?> assignRolesToUser(@PathVariable Long userId,
            @Valid @RequestBody AssignRoleRequest request) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        Set<Role> newRoles = new HashSet<>();

        for (String roleName : request.getRoleNames()) {
            Optional<Role> roleOpt = roleRepository.findByName(roleName);
            if (roleOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Error: Role " + roleName + " not found!");
            }
            newRoles.add(roleOpt.get());
        }

        user.setRoles(newRoles);
        userRepository.save(user);

        return ResponseEntity.ok("Roles assigned successfully!");
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}