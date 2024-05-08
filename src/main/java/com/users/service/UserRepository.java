package com.users.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.users.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

}
