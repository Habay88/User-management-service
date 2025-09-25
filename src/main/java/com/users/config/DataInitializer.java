package com.users.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.users.entity.Role;
import com.users.entity.User;
import com.users.repository.RoleRepository;
import com.users.repository.UserRepository;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create system roles
        if (roleRepository.count() == 0) {
            Role superAdminRole = new Role("SUPER_ADMIN", "Super Administrator with full system access");
            superAdminRole.setSystemRole(true);
            roleRepository.save(superAdminRole);

            Role adminRole = new Role("ADMIN", "Administrator with management privileges");
            adminRole.setSystemRole(true);
            roleRepository.save(adminRole);

            Role userRole = new Role("USER", "Regular user with basic access");
            userRole.setSystemRole(true);
            roleRepository.save(userRole);
        }

        // Create super admin user
        if (userRepository.findByUsername("superadmin").isEmpty()) {
            Role superAdminRole = roleRepository.findByName("SUPER_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Super Admin role not found"));

            User superAdmin = new User();
            superAdmin.setUsername("superadmin");
            superAdmin.setEmail("superadmin@usermanagementservice.com");
            superAdmin.setPassword(passwordEncoder.encode("SuperAdmin123!"));
            superAdmin.setRoles(Collections.singleton(superAdminRole));
            userRepository.save(superAdmin);
        }
    }
}
