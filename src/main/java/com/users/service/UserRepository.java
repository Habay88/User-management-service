package com.users.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.users.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    void deleteByEmail(String email);

}
