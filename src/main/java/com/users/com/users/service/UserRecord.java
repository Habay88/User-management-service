package com.users.com.users.service;

import java.util.Set;

import com.users.entity.Roles;

public record UserRecord(Long id, String firstName, String lastName, String email, Set<Roles> roles)

{
}