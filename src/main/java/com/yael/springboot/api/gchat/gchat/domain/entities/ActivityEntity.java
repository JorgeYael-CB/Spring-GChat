package com.yael.springboot.api.gchat.gchat.domain.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ManyToMany;



public class ActivityEntity extends BaseEntity {
    private String activity;

    @ManyToMany
    private List<UserEntity> users = new ArrayList<>();


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}
