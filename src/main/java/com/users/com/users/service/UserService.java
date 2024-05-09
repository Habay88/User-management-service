package com.users.com.users.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.users.com.users.exception.UserAlreadyExistsException;
import com.users.com.users.exception.UserNotFoundException;
import com.users.entity.Roles;
import com.users.entity.Users;
import com.users.service.RoleRepository;
import com.users.service.UserRepository;

import jakarta.transaction.Transactional;

public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Users add(Users user) {
        Optional<Users> theUser = userRepository.findByEmail(user.getEmail());
        if (theUser.isPresent()) {
            throw new UserAlreadyExistsException("A user with " + user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Roles role = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(role));
        return userRepository.save(user);
    }

    @Override
    public List<UserRecord> getAllUsers() {
        return userRepository.findAll()
                .stream().map(user -> new UserRecord(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        new HashSet<>(user.getRoles())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public Users getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public Users update(Users user) {
        user.setRoles(user.getRoles());
        return userRepository.save(user);
    }
}