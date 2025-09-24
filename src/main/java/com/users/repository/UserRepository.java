package com.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);

    Optional<User> findByUsername(String username);

}
