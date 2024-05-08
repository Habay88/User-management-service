package com.users.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Data
@Entity
public class Roles {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
      @NaturalId
    private String name;


@JsonIgnore
@ManyToMany(mappedBy = "roles")
    private Collection<Users> users= new HashSet<>();
    public Roles(String name) {
        this.name = name;
    }
     public void removeAllUsersFromRole(){
        if (this.getUsers() != null){
            List<Users> usersInRole = this.getUsers().stream().toList();
            usersInRole.forEach(this::removeUserFromRole);
        }
     }
    public void removeUserFromRole(Users user) {
        user.getRoles().remove(this);
        this.getUsers().remove(user);
    }
    public void assignUserToRole(Users users){
        users.getRoles().add(this);
        this.getUsers().add(users);
    }
}
