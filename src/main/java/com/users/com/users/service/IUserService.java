package com.users.com.users.service;

import java.util.List;

import com.users.entity.Users;

public interface IUserService {

    Users add(Users user);

    List<UserRecord> getAllUsers();

    void delete(String email);

    Users getUser(String email);

    Users update(Users user);
}