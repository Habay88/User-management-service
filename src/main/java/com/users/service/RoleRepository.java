package com.users.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.entity.Roles;

public interface RoleRepository  extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);
}
