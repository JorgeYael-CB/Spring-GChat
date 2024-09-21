package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.List;




public class RoleEntity {

    private Long id;
    private String role;

    // ManyToMany
    private List<UserEntity> users;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}
